package com.best.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2015/11/26.
 */
public class UserCollection extends BmobObject {
    private String usename,title,work_address,over_time,money,recruittableID;

    public UserCollection() {
    }

    public UserCollection(String tableName, String usename, String title, String work_address, String over_time, String money, String recruittableID) {
        super(tableName);
        this.usename = usename;
        this.title = title;
        this.work_address = work_address;
        this.over_time = over_time;
        this.money = money;
        this.recruittableID = recruittableID;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWork_address() {
        return work_address;
    }

    public void setWork_address(String work_address) {
        this.work_address = work_address;
    }

    public String getOver_time() {
        return over_time;
    }

    public void setOver_time(String over_time) {
        this.over_time = over_time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRecruittableID() {
        return recruittableID;
    }

    public void setRecruittableID(String recruittableID) {
        this.recruittableID = recruittableID;
    }
}
