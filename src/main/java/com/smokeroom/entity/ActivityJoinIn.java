package com.smokeroom.entity;

import java.util.Date;
/**
 * 活动报名实体类*/
public class ActivityJoinIn {
	private Integer ji_id;
    
	private Integer ji_at_id;

    private Integer ji_uid;

    private Date ji_creation;
    
    //报名名字
    private String ji_enroll_name;
    
    //报名电话
    private  String ji_enroll_phone;

    public String getJi_enroll_name() {
		return ji_enroll_name;
	}

	public void setJi_enroll_name(String ji_enroll_name) {
		this.ji_enroll_name = ji_enroll_name;
	}

	public String getJi_enroll_phone() {
		return ji_enroll_phone;
	}

	public void setJi_enroll_phone(String ji_enroll_phone) {
		this.ji_enroll_phone = ji_enroll_phone;
	}

	public Integer getJi_id() {
		return ji_id;
	}

	public void setJi_id(Integer ji_id) {
		this.ji_id = ji_id;
	}

	public Integer getJi_at_id() {
        return ji_at_id;
    }

    public void setJi_at_id(Integer ji_at_id) {
        this.ji_at_id = ji_at_id;
    }

    public Integer getJi_uid() {
        return ji_uid;
    }

    public void setJi_uid(Integer ji_uid) {
        this.ji_uid = ji_uid;
    }

    public Date getJi_creation() {
        return ji_creation;
    }

    public void setJi_creation(Date ji_creation) {
        this.ji_creation = ji_creation;
    }

	@Override
	public String toString() {
		return "ActivityJoinIn [ji_id=" + ji_id + ", ji_at_id=" + ji_at_id + ", ji_uid=" + ji_uid + ", ji_creation="
				+ ji_creation + ", ji_enroll_name=" + ji_enroll_name + ", ji_enroll_phone=" + ji_enroll_phone + "]";
	}

	
    
}