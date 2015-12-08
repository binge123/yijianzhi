package com.best.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by dell2 on 2015/12/7.
 */
public class IdentificationTable extends BmobObject {
    private String username;
    private String image;
    private String identificationstate;
    private String companyname;
    private String phone_number;

    public IdentificationTable(String username, String companyname, String image, String identificationstate,String phone_number) {
        this.username = username;
        this.companyname = companyname;
        this.image = image;
        this.identificationstate = identificationstate;
        this.phone_number = phone_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIdentificationstate() {
        return identificationstate;
    }

    public void setIdentificationstate(String identificationstate) {
        this.identificationstate = identificationstate;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
