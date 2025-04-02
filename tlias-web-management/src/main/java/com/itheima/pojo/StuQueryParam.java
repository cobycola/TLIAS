package com.itheima.pojo;


import lombok.Data;

@Data
public class StuQueryParam {
    private Integer page=1;
    private Integer pageSize=10;
    private String name;
    private Integer degree;
    private Integer clazzId;
    private Short gender;
}
