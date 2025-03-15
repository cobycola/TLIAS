package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    //查询全部部门数据
    @GetMapping("/depts")
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }

    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询部门数据，id：{}",id);
        Dept dept = deptService.getInfo(id);
        return Result.success(dept);
    }

    @DeleteMapping("/depts")
    public Result delete(Integer id){
        log.info("根据ID删除部门数据，id：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/depts")
    public Result save(@RequestBody Dept dept){
        log.info("新增部门数据，dept：{}",dept);
        deptService.save(dept);
        return Result.success();
    }

    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info("修改部门数据，dept：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
