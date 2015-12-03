package com.best.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2015/11/25.
 */
public class RecruitTable extends BmobObject {
    private  String work_address;
    private  String recruitid;
    private  String position_desc;
    private  String contacts;
    private  String person_number;
    private  String title;
    private  String jiesuan;
    private  String name;
    private  String over_time;
    private  String work_money;
    private  String position;
    public RecruitTable() {
    }

    public RecruitTable(String tableName, String work_address,
                        String recruitid, String position_desc,
                        String contacts, String person_number,
                        String title,String over_time,
                        String work_money,String position,
                        String name) {
        super(tableName);
        this.work_address = work_address;
        this.recruitid = recruitid;
        this.position_desc = position_desc;
        this.contacts = contacts;
        this.person_number = person_number;
        this.title = title;
        this.over_time = over_time;
        this.work_money = work_money;
        this.position = position;
        this.name = name;
    }

    public String getJiesuan() {
        return jiesuan;
    }

    public void setJiesuan(String jiesuan) {
        this.jiesuan = jiesuan;
    }

    public String getWork_address() {
        return work_address;
    }

    public void setWork_address(String work_address) {
        this.work_address = work_address;
    }

    public String getRecruitid() {
        return recruitid;
    }

    public void setRecruitid(String recruitid) {
        this.recruitid = recruitid;
    }

    public String getPosition_desc() {
        return position_desc;
    }

    public void setPosition_desc(String position_description) {
        this.position_desc = position_desc;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPerson_number() {
        return person_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPerson_number(String person_number) {
        this.person_number = person_number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOver_time() {
        return over_time;
    }

    public void setOver_time(String over_time) {
        this.over_time = over_time;
    }

    public String getWork_money() {
        return work_money;
    }

    public void setWork_money(String work_money) {
        this.work_money = work_money;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
