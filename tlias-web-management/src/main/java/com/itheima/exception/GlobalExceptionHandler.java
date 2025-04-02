package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final HttpMessageConverters message;

    public GlobalExceptionHandler(HttpMessageConverters message) {
        this.message = message;
    }

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("异常信息：{}",e.getMessage());
        return Result.error("出错了 请联系管理员");
    }
    //按照异常类的继承关系从下往上匹配
    @ExceptionHandler
    public Result handleDumplicateKeyException(DuplicateKeyException e){
        log.error("异常信息：{}",e.getMessage());
        String message=e.getMessage();
        int i=message.indexOf("Duplicate entry");
        String errMsg=message.substring(i);
        String[]arr=errMsg.split(" ");
        return Result.error(arr[2]+"已存在");
    }
}
