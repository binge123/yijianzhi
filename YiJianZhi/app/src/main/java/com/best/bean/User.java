package com.best.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2015/11/23.
 */
public class User extends BmobObject{
    private String username;
    private String password;
    private String nick;
    private String user_sex;
    private String user_age;
    private String user_address;
    private String user_credit;
    private String renzheng;
    private String registtime;
    private String last_login_time;
    private String phone_number;

    public User(String tableName, String username,
                String password, String nick,
                String user_sex, String user_age,
                String user_address, String user_credit,
                String renzheng, String registtime,
                String last_login_time, String phone_number) {
        this.username = username;
        this.password = password;
        this.nick = nick;
        this.user_sex = user_sex;
        this.user_age = user_age;
        this.user_address = user_address;
        this.user_credit = user_credit;
        this.renzheng = renzheng;
        this.registtime = registtime;
        this.last_login_time = last_login_time;
        this.phone_number = phone_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_age() {
        return user_age;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_credit() {
        return user_credit;
    }

    public void setUser_credit(String user_credit) {
        this.user_credit = user_credit;
    }

    public String getRenzheng() {
        return renzheng;
    }

    public void setRenzheng(String renzheng) {
        this.renzheng = renzheng;
    }

    public String getRegisttime() {
        return registtime;
    }

    public void setRegisttime(String registtime) {
        this.registtime = registtime;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
