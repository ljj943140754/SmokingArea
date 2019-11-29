package com.smokeroom.entity;

public class ActivityTest{
	  
	private String ur_nickname;
	private String ji_creation;
	private String ur_avatarurl;
	public String getUr_nickname() {
		return ur_nickname;
	}
	public void setUr_nickname(String ur_nickname) {
		this.ur_nickname = ur_nickname;
	}
	public String getJi_creation() {
		return ji_creation;
	}
	public void setJi_creation(String ji_creation) {
		this.ji_creation = ji_creation;
	}
	public String getUr_avatarurl() {
		return ur_avatarurl;
	}
	public void setUr_avatarurl(String ur_avatarurl) {
		this.ur_avatarurl = ur_avatarurl;
	}
	@Override
	public String toString() {
		return "ActivityBO [ur_nickname=" + ur_nickname + ", ji_creation=" + ji_creation + ", ur_avatarurl="
				+ ur_avatarurl + "]";
	}
}
