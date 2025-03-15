package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
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
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }
}
