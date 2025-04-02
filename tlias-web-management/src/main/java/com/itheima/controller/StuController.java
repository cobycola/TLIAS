package com.itheima.controller;


import com.itheima.pojo.*;
import com.itheima.service.StuService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StuController {
    @Resource
    private StuService stuService;

    @RequestMapping()
    public Result page(StuQueryParam stuQueryParam) {
        log.info("分页查询：{}",stuQueryParam);
        PageResult<Stu> pageResult= stuService.page(stuQueryParam);
        return Result.success(pageResult);
    }
    @DeleteMapping("/{ids}")
    public Result deleteByIds(List<Integer> ids) {
        log.info("批量删除：{}",ids);
        stuService.deleteByIds(ids);
        return Result.success();
    }

}
