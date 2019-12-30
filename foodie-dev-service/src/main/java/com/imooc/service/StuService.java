package com.imooc.service;

import com.imooc.pojo.Stu;

/**
 * StuService接口用于测试基于通用Mapper的rest接口
 */
public interface StuService {

    public Stu getStuInfo(int id);

    public void saveStu();

    public void updateStu(int id);

    public void deleteStu(int id);

    public void saveParent();

    public void saveChildren();
}
