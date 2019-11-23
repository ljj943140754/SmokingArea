package com.smokeroom.entity;

import java.util.Date;
/**
 * 活动实体类*/
public class Activity extends pageObject{
    private Integer at_id;

    private Integer at_uid;

    private String at_title;

    private Integer at_status;

    private Integer at_max_count;

    private Date at_creation;

    private String at_content;
    
    private Integer at_sign_count;
    
    private Date at_start_date;
    
    private Date at_end_date;
    
    private String at_issuer;
    
    private String at_address;
    
    public Date getAt_start_date() {
		return at_start_date;
	}

	public void setAt_start_date(Date at_start_date) {
		this.at_start_date = at_start_date;
	}

	public Date getAt_end_date() {
		return at_end_date;
	}

	public void setAt_end_date(Date at_end_date) {
		this.at_end_date = at_end_date;
	}


	public String getAt_issuer() {
		return at_issuer;
	}

	public void setAt_issuer(String at_issuer) {
		this.at_issuer = at_issuer;
	}
	
	public String getAt_address() {
		return at_address;
	}

	public void setAt_address(String at_address) {
		this.at_address = at_address;
	}

	public Integer getAt_sign_count() {
		return at_sign_count;
	}

	public void setAt_sign_count(Integer at_sign_count) {
		this.at_sign_count = at_sign_count;
	}

	public Integer getAt_id() {
        return at_id;
    }

    public void setAt_id(Integer at_id) {
        this.at_id = at_id;
    }

    public Integer getAt_uid() {
        return at_uid;
    }

    public void setAt_uid(Integer at_uid) {
        this.at_uid = at_uid;
    }

    public String getAt_title() {
        return at_title;
    }

    public void setAt_title(String at_title) {
        this.at_title = at_title;
    }

    public Integer getAt_status() {
        return at_status;
    }

    public void setAt_status(Integer at_status) {
        this.at_status = at_status;
    }

    public Integer getAt_max_count() {
        return at_max_count;
    }

    public void setAt_max_count(Integer at_max_count) {
        this.at_max_count = at_max_count;
    }

    public Date getAt_creation() {
        return at_creation;
    }

    public void setAt_creation(Date at_creation) {
        this.at_creation = at_creation;
    }

    public String getAt_content() {
        return at_content;
    }

    public void setAt_content(String at_content) {
        this.at_content = at_content;
    }

	@Override
	public String toString() {
		return "Activity [at_id=" + at_id + ", at_uid=" + at_uid + ", at_title=" + at_title + ", at_status=" + at_status
				+ ", at_max_count=" + at_max_count + ", at_creation=" + at_creation + ", at_content=" + at_content
				+ ", at_sign_count=" + at_sign_count + ", at_start_date=" + at_start_date + ", at_end_date="
				+ at_end_date + ", at_issuer=" + at_issuer + ", at_address=" + at_address + "]";
	}
    
}