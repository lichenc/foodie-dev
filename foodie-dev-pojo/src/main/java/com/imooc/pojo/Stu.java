package com.imooc.pojo;

import javax.persistence.Id;

public class Stu {
    /**
     * 学生标识
     */
    @Id
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 获取学生标识
     *
     * @return id - 学生标识
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置学生标识
     *
     * @param id 学生标识
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}