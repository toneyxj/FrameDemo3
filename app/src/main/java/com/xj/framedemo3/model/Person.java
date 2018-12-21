package com.xj.framedemo3.model;

import com.xj.mainframe.configer.APPLog;

public class Person {

    private String name;
    private int age;
    private boolean sex;

    public Person() {
    }

    public Person(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        APPLog.e("在这里实列话参数了");
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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    private void setSay(String value){
        APPLog.e("私有的say已经调用了");
    }
}
