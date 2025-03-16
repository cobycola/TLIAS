package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpLogMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        PageHelper.
//        Long Total=empMapper.count();
//        List<Emp> rows=empMapper.list((page-1)*pageSize,pageSize);
//        return new PageResult<Emp>(Total,rows);
//    }
    @Override
    public PageResult<Emp>page(EmpQueryParam empQueryParam){
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //2.执行查询
        List<Emp>empList=empMapper.list(empQueryParam);
        //3.解析查询结果，并封装
        Page<Emp>p=(Page<Emp>)empList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Transactional(rollbackFor={Exception.class}) //事务管理
    @Override
    public void save(Emp emp) {
        try {
            //1.保存员工基本信息
            LocalDateTime localDateTime = LocalDateTime.now();
            emp.setCreateTime(localDateTime);
            emp.setUpdateTime(localDateTime);
            empMapper.insert(emp);
            //2.保存员工的工作经历 - 批量插入
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //3.记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工" + emp.toString());
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor={Exception.class}) //事务管理
    @Override
    public void deleteById(List<Integer> ids) {
        //1.删除员工基本信息
        empMapper.deleteByIds(ids);
        //2.删除员工的工作经历
        empExprMapper.deleteByEmpIds(ids);
        //3.记录操作日志
        EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "删除员工" + ids.toString());
        empLogService.insertLog(empLog);
    }

    @Override
    public Emp getInfo(Integer id) {
        Emp emp = empMapper.getById(id);
        return emp;
    }

    @Transactional(rollbackFor={Exception.class})
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        Integer empId=emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }
}
