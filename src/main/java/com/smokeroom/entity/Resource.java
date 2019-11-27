package com.smokeroom.entity;

import java.util.Date;

public class Resource {
	private Integer rs_id;
	 
	private Date rs_creation;
	 
	private String rs_url;

	public Integer getRs_id() {
		return rs_id;
	}

	public void setRs_id(Integer rs_id) {
		this.rs_id = rs_id;
	}

	public Date getRs_creation() {
		return rs_creation;
	}

	public void setRs_creation(Date rs_creation) {
		this.rs_creation = rs_creation;
	}

	public String getRs_url() {
		return rs_url;
	}

	public void setRs_url(String rs_url) {
		this.rs_url = rs_url;
	}

	@Override
	public String toString() {
		return "Resource [rs_id=" + rs_id + ", rs_creation=" + rs_creation + ", rs_url=" + rs_url + "]";
	}
	 
	
}
