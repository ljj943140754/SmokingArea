package com.smokeroom.entity.ext;

import java.util.Date;

public class userTraceVO {
	private Integer te_u_id;
	
	private Integer te_fy_id;
	
	//纬度
	private String fy_lat;
	
	//经度
	private String fy_lng;
	
	private Date te_fy_creationtime;
	
	private String fy_detail;
	
	private String fy_name;
	
	
	

	public Date getTe_fy_creationtime() {
		return te_fy_creationtime;
	}

	public void setTe_fy_creationtime(Date te_fy_creationtime) {
		this.te_fy_creationtime = te_fy_creationtime;
	}

	public String getFy_detail() {
		return fy_detail;
	}

	public void setFy_detail(String fy_detail) {
		this.fy_detail = fy_detail;
	}

	public String getFy_name() {
		return fy_name;
	}

	public void setFy_name(String fy_name) {
		this.fy_name = fy_name;
	}

	public Integer getTe_u_id() {
		return te_u_id;
	}

	public void setTe_u_id(Integer te_u_id) {
		this.te_u_id = te_u_id;
	}

	public Integer getTe_fy_id() {
		return te_fy_id;
	}

	public void setTe_fy_id(Integer te_fy_id) {
		this.te_fy_id = te_fy_id;
	}

	public String getFy_lat() {
		return fy_lat;
	}

	public void setFy_lat(String fy_lat) {
		this.fy_lat = fy_lat;
	}

	public String getFy_lng() {
		return fy_lng;
	}

	public void setFy_lng(String fy_lng) {
		this.fy_lng = fy_lng;
	}

	@Override
	public String toString() {
		return "userTraceVO [te_u_id=" + te_u_id + ", te_fy_id=" + te_fy_id + ", fy_lat=" + fy_lat + ", fy_lng="
				+ fy_lng + ", te_fy_creationtime=" + te_fy_creationtime + ", fy_detail=" + fy_detail + ", fy_name="
				+ fy_name + "]";
	}
	
	
}
