package com.imooc.controller;

import com.imooc.pojo.Stu;
import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class StuController {

    @Autowired
    private StuService stuService;

    @GetMapping("/getStuInfo")
    @ResponseBody
    public Stu getStuInfo(int id){
        return stuService.getStuInfo(id);
    }

    @PostMapping("/saveStu")
    @ResponseBody
    public String saveStu(){
        stuService.saveStu();
        return "OK";
    }

    @PostMapping("/updateStu")
    @ResponseBody
    public String updateStu(int id){
        stuService.updateStu(id);
        return "OK";
    }

    @PostMapping("/deleteStu")
    @ResponseBody
    public String deleteStu(int id){
        stuService.deleteStu(id);
        return "OK";
    }
}
