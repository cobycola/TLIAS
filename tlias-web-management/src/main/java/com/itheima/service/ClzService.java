package com.itheima.service;


import com.itheima.pojo.Clz;
import com.itheima.pojo.ClzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClzService {

    PageResult<Clz> page(ClzQueryParam clzQueryParam);

    void deleteByIds(List<Integer> ids);

    void insert(Clz clz);

    Clz getById(Integer id);

    void update(Clz clz);

    List<Clz> list();
}
