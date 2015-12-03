package com.best.bean;

import java.security.PrivateKey;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2015/11/25.
 */
public class ResumeTable extends BmobObject {
    private String name,work_exp,work_time,expect_money,age,sex,zhiwei,address,remark;

    public ResumeTable() {
    }

    public ResumeTable(String tableName, String name, String work_exp, String work_time, String expect_money, String age, String sex, String zhiwei, String address, String remark) {
        super(tableName);
        this.name = name;
        this.work_exp = work_exp;
        this.work_time = work_time;
        this.expect_money = expect_money;
        this.age = age;
        this.sex = sex;
        this.zhiwei = zhiwei;
        this.address = address;
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWork_exp() {
        return work_exp;
    }

    public void setWork_exp(String work_exp) {
        this.work_exp = work_exp;
    }

    public String getWork_time() {
        return work_time;
    }

    public void setWork_time(String work_time) {
        this.work_time = work_time;
    }

    public String getExpect_money() {
        return expect_money;
    }

    public void setExpect_money(String expect_money) {
        this.expect_money = expect_money;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getZhiwei() {
        return zhiwei;
    }

    public void setZhiwei(String zhiwei) {
        this.zhiwei = zhiwei;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
