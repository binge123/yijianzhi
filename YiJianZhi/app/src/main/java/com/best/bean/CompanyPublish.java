package com.best.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by dell2 on 2015/11/26.
 */
public class CompanyPublish extends BmobObject {
    private String username;
    private String title;
    private String work_address;
    private String over_time;
    private String money;
    private String recruittableid;

    public CompanyPublish(String username, String title, String work_address, String over_time, String money, String recruittableid) {
        this.username = username;
        this.title = title;
        this.work_address = work_address;
        this.over_time = over_time;
        this.money = money;
        this.recruittableid = recruittableid;
    }

    public CompanyPublish(String title, String money) {
        this.title = title;
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRecruittableid() {
        return recruittableid;
    }

    public void setRecruittableid(String recruittableid) {
        this.recruittableid = recruittableid;
    }
}
