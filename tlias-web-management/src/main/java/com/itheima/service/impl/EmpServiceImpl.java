package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpLogMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogMapper empLogMapper;
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
    public void save(Emp emp){
        try{
            //1.保存员工基本信息
            LocalDateTime localDateTime  = LocalDateTime.now();
            emp.setCreateTime(localDateTime);
            emp.setUpdateTime(localDateTime);
            empMapper.insert(emp);
            //2.保存员工的工作经历 - 批量插入
            List<EmpExpr> exprList= emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr->{
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        }finally{
            //3.记录操作日志
            EmpLog empLog=new EmpLog(null,LocalDateTime.now(),"新增员工"+emp.toString());
            empLogMapper.insert(empLog);
        }

    }
}
