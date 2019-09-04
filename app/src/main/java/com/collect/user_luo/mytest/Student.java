package com.collect.user_luo.mytest;

/**
 * 作者：罗发新
 * 时间：2019/1/4 0004    16:15
 * 邮件：424533553@qq.com
 * 说明：
 */
public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
