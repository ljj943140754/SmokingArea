package com.smokeroom.entity;

import java.util.Date;

//用户实体表
public class User {
    private Integer ur_id;

    private String ur_openid;

    private String ur_wechat_id;

    private String ur_phone;

    private String ur_nickname;

    private String ur_unionid;

    private Integer ur_scores;

    private Date ur_creation;

    private Date ur_lastupdated;
    
    private String ur_avatarurl;

    public String getUr_avatarurl() {
		return ur_avatarurl;
	}

	public void setUr_avatarurl(String ur_avatarurl) {
		this.ur_avatarurl = ur_avatarurl;
	}

	public Integer getUr_id() {
        return ur_id;
    }

    public void setUr_id(Integer ur_id) {
        this.ur_id = ur_id;
    }

    public String getUr_openid() {
        return ur_openid;
    }

    public void setUr_openid(String ur_openid) {
        this.ur_openid = ur_openid;
    }

    public String getUr_wechat_id() {
        return ur_wechat_id;
    }

    public void setUr_wechat_id(String ur_wechat_id) {
        this.ur_wechat_id = ur_wechat_id;
    }

    public String getUr_phone() {
        return ur_phone;
    }

    public void setUr_phone(String ur_phone) {
        this.ur_phone = ur_phone;
    }

    public String getUr_nickname() {
        return ur_nickname;
    }

    public void setUr_nickname(String ur_nickname) {
        this.ur_nickname = ur_nickname;
    }

    public String getUr_unionid() {
        return ur_unionid;
    }

    public void setUr_unionid(String ur_unionid) {
        this.ur_unionid = ur_unionid;
    }

    public Integer getUr_scores() {
        return ur_scores;
    }

    public void setUr_scores(Integer ur_scores) {
        this.ur_scores = ur_scores;
    }

    public Date getUr_creation() {
        return ur_creation;
    }

    public void setUr_creation(Date ur_creation) {
        this.ur_creation = ur_creation;
    }

    public Date getUr_lastupdated() {
        return ur_lastupdated;
    }

    public void setUr_lastupdated(Date ur_lastupdated) {
        this.ur_lastupdated = ur_lastupdated;
    }

	@Override
	public String toString() {
		return "User [ur_id=" + ur_id + ", ur_openid=" + ur_openid + ", ur_wechat_id=" + ur_wechat_id + ", ur_phone="
				+ ur_phone + ", ur_nickname=" + ur_nickname + ", ur_unionid=" + ur_unionid + ", ur_scores=" + ur_scores
				+ ", ur_creation=" + ur_creation + ", ur_lastupdated=" + ur_lastupdated + ", ur_avatarurl="
				+ ur_avatarurl + "]";
	}
 
}