package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Stu;
import com.itheima.pojo.StuQueryParam;

import java.util.List;

public interface StuService {
    PageResult<Stu> page(StuQueryParam stuQueryParam);

    void deleteByIds(List<Integer> ids);
}
