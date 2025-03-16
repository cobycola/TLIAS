package com.itheima.mapper;


import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public Long count();
//
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize}")

    List<Emp> list(EmpQueryParam empQueryParam);

    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String,Object>> countEmpGenderData();
}
