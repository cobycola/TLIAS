package com.itheima.controller;


import com.github.pagehelper.Page;
import com.itheima.exception.BusinessException;
import com.itheima.pojo.Clz;
import com.itheima.pojo.ClzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClzService;
import com.itheima.service.impl.ClzServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClzController {

    @Autowired
    private ClzService clzService;

    @GetMapping
    public Result page(ClzQueryParam clzQueryParam){
        log.info("查询所有班级信息...");
        PageResult<Clz>pageResult = clzService.page(clzQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{ids}")
    public Result deleteById(@PathVariable List<Integer> ids){
        log.info("批量删除...{}",ids);
        clzService.deleteByIds(ids);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Clz clz) {
        try {
            log.info("添加班级:{}", clz);
            clzService.insert(clz);
            return Result.success();
        } catch (BusinessException e) {
            log.error("添加班级失败:{}", clz);
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询班级{}",id);
        Clz clz=clzService.getById(id);
        return Result.success(clz);
    }

    @PutMapping
    public Result update(@RequestBody Clz clz) {
        try{
            log.info("修改班级信息:{}",clz);
            clzService.update(clz);
            return Result.success(clz);
        }catch (BusinessException e){
            log.error("修改班级失败:{}",clz);
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/list")
    public Result list(){
        log.info("输出所有班级信息");
        List<Clz> list = clzService.list();
        return Result.success(list);
    }
}
