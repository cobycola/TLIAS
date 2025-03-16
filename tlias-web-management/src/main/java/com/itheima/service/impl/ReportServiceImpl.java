package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list=empMapper.countEmpJobData();
        List<Object>jobList=list.stream().map(dataMap->dataMap.get("pos")).toList();
        List<Object>countList=list.stream().map(dataMap->dataMap.get("num")).toList();
        return new JobOption(jobList,countList);
    }
    @Override
    public List<Map<String,Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
}
