package com.itheima.controller;


import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping()
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询：{}",empQueryParam);
        PageResult<Emp> pageResult= empService.page(empQueryParam);
        return Result.success(pageResult);
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工数据：{}",id);
        Emp emp= empService.getInfo(id);
        return Result.success(emp);
    }
    @PostMapping()
    public Result save(@RequestBody Emp emp){
        log.info("新增员工数据：{}",emp);
        empService.save(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工数据，id：{}",ids);
        empService.deleteById(ids);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工数据：{}",emp);
        empService.update(emp);
        return Result.success(emp);
    }


}
