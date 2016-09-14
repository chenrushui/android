package com.crs.demo.bean;

import java.io.Serializable;

/**
 * Created on 2016/8/23.
 * Author:crs
 * Description:学生实体类
 */
public class StudentEntity implements Serializable {
    private String name;
    private int age;

    public StudentEntity() {
    }

    public StudentEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
