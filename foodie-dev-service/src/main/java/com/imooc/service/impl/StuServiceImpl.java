package com.imooc.service.impl;

import com.imooc.mapper.StuMapper;
import com.imooc.pojo.Stu;
import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("stuService")
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveStu() {
        Stu stu = new Stu();
        stu.setAge(20);
        stu.setName("jack");
        stuMapper.insert(stu);
    }

    @Override
    public void updateStu(int id) {
        Stu stu = new Stu();
        stu.setId(id);
        stu.setAge(21);
        stu.setName("jack1");
        stuMapper.updateByPrimaryKey(stu);
    }

    @Override
    public void deleteStu(int id) {
        stuMapper.deleteByPrimaryKey(id);
    }


    public void saveParent() {
        Stu stu = new Stu();
        stu.setName("parent");
        stu.setAge(19);
        stuMapper.insert(stu);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void saveChildren() {
        saveChild1();
        int a = 1 / 0;
        saveChild2();
    }

    public void saveChild1() {
        Stu stu = new Stu();
        stu.setName("child1");
        stu.setAge(6);
        stuMapper.insert(stu);
    }

    public void saveChild2() {
        Stu stu = new Stu();
        stu.setName("child2");
        stu.setAge(7);
        stuMapper.insert(stu);
    }


}
