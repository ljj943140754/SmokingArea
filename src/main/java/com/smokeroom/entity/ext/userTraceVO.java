package com.smokeroom.entity.ext;

public class userTraceVO {
	private Integer te_u_id;
	
	private Integer te_fy_id;
	
	//纬度
	private String fy_lat;
	
	//经度
	private String fy_lng;

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
				+ fy_lng + "]";
	}
	
	
}
