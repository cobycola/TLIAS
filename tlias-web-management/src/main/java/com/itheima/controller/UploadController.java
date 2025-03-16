package com.itheima.controller;


import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接受参数：{},{},{}", name, age, file);
        String originalFilename = file.getOriginalFilename();
        String extension= originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName= UUID.randomUUID().toString()+extension;
        file.transferTo(new File("D:/java学习/web-ai-project02/tlias-web-management/src/main/resources/upload/" + newFileName));
        return Result.success();
    }
}
