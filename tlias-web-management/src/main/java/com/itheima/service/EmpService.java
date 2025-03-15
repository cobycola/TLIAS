package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;

public interface EmpService {

    PageResult<Emp> page(EmpQueryParam empQueryParam);
}
