package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StuMapper;
import com.itheima.pojo.*;
import com.itheima.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;
    @Override
    public PageResult<Stu> page(StuQueryParam stuQueryParam){
        //1.设置分页参数
        PageHelper.startPage(stuQueryParam.getPage(), stuQueryParam.getPageSize());
        //2.执行查询
        List<Stu> stuList=stuMapper.list(stuQueryParam);
        //3.解析查询结果，并封装
        Page<Stu> p=(Page<Stu>)stuList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        stuMapper.deleteByIds(ids);
    }
}
