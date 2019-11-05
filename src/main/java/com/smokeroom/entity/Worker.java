package com.smokeroom.entity;

import java.util.Date;

public class Worker {
    private Integer wk_id;

    private Integer wk_num;

    private String wk_type;

    private String wk_phone;

    private String wk_password;

    private String wk_openid;

    private String wk_name;

    private Date wk_creation;
    
    private Integer wk_isdel;

    public Integer getWk_isdel() {
		return wk_isdel;
	}

	public void setWk_isdel( Integer  wk_isdel) {
		this.wk_isdel = wk_isdel;
	}

	public Integer getWk_id() {
        return wk_id;
    }

    public void setWk_id(Integer wk_id) {
        this.wk_id = wk_id;
    }

    public Integer getWk_num() {
        return wk_num;
    }

    public void setWk_num(Integer wk_num) {
        this.wk_num = wk_num;
    }

    public String getWk_type() {
        return wk_type;
    }

    public void setWk_type(String wk_type) {
        this.wk_type = wk_type;
    }

    public String getWk_phone() {
        return wk_phone;
    }

    public void setWk_phone(String wk_phone) {
        this.wk_phone = wk_phone;
    }

    public String getWk_password() {
        return wk_password;
    }

    public void setWk_password(String wk_password) {
        this.wk_password = wk_password;
    }

    public String getWk_openid() {
        return wk_openid;
    }

    public void setWk_openid(String wk_openid) {
        this.wk_openid = wk_openid;
    }

    public String getWk_name() {
        return wk_name;
    }

    public void setWk_name(String wk_name) {
        this.wk_name = wk_name;
    }

    public Date getWk_creation() {
        return wk_creation;
    }

    public void setWk_creation(Date wk_creation) {
        this.wk_creation = wk_creation;
    }
}