package com.itheima.service.impl;

import com.github.pagehelper.BoundSqlInterceptor;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.exception.BusinessException;
import com.itheima.mapper.ClzMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Clz;
import com.itheima.pojo.ClzQueryParam;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferStrategy;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClzServiceImpl implements ClzService {

    @Autowired
    private ClzMapper clzMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult<Clz> page(ClzQueryParam clzQueryParam) {
        PageHelper.startPage(clzQueryParam.getPage(), clzQueryParam.getPageSize());
        List<Clz> clzList = clzMapper.list(clzQueryParam);
        clzList.forEach(clz -> {
            LocalDate now=LocalDate.now();
            if(now.isAfter(clz.getEndDate())){
                clz.setStatus("已结课");
            }else if(now.isAfter(clz.getBeginDate())){
                clz.setStatus("已开班");
            }else {
                clz.setStatus("未开班");
            }
        });
        Page<Clz>p=(Page<Clz>)clzList;

        return new PageResult<Clz>(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        clzMapper.deleteByIds(ids);
    }

    @Override
    public void insert(Clz clz) {
        Clz isExistingClz=clzMapper.getById(clz.getId());
        if(isExistingClz!=null){
            throw new BusinessException("班级名称已存在");
        }
        if(clz.getMasterId()!=null){
            Emp emp=empMapper.getById(clz.getMasterId());
            if(emp!=null&&emp.getJob()==1){
                clz.setMasterName(emp.getName());
            }else{
                throw new BusinessException("不存在该班主任");
            }
        }
        LocalDateTime now=LocalDateTime.now();
        clz.setCreateTime(now);
        clz.setUpdateTime(now);
        clzMapper.insert(clz);
    }

    @Override
    public void getById(Integer id) {
        clzMapper.getById(id);
    }

    @Override
    public void update(Clz clz) {
        // 如果设置了班主任ID，需要验证班主任
        if (clz.getMasterId() != null) {
            Emp emp = empMapper.getById(clz.getMasterId());
            if (emp != null && emp.getJob() == 1) {
                clz.setMasterName(emp.getName());
            } else {
                throw new BusinessException("不存在该班主任");
            }
        }
        // 设置更新时间
        clz.setUpdateTime(LocalDateTime.now());
        // 执行更新操作
        clzMapper.update(clz);
    }

    @Override
    public List<Clz> list() {
        List<Clz>list=clzMapper.list(null);
        return list;
    }
}
