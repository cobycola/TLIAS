package com.itheima.mapper;

import com.itheima.pojo.Clz;
import com.itheima.pojo.ClzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClzMapper {

    List<Clz> list(ClzQueryParam clzQueryParam);

    void deleteByIds(List<Integer> ids);

    void insert(Clz clz);

    Clz getById(Integer id);

    void update(Clz clz);
}
