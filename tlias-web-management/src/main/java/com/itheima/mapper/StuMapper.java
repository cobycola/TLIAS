package com.itheima.mapper;

import com.itheima.pojo.Stu;
import com.itheima.pojo.StuQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StuMapper {

    List<Stu> list(StuQueryParam stuQueryParam);

    void deleteByIds(List<Integer>ids);
}
