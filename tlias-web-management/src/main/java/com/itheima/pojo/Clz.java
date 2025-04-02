package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clz {
    private Integer id; //ID,主键
    private String name; //班级名
    private String subject; //科目
    private String room; //教室

    private Integer masterId; //班主任ID
    private String masterName; //班主任姓名

    private String status; //状态 未开班 开班 已结课
    private LocalDate beginDate; //开班日期
    private LocalDate endDate; //结业日期

    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
