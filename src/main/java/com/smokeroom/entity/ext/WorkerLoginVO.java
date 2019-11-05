package com.smokeroom.entity.ext;
/**
 * 员工登陆对象。
 * @author Administrator
 *
 */
public class WorkerLoginVO {
	private String phone;
	private String code;
	private long t1 = System.currentTimeMillis();
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getT1() {
		return t1;
	}
	public void setT1(long t1) {
		this.t1 = t1;
	}
	
}
