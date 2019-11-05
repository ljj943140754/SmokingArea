package com.common.enu;

public enum Role {
	/**
	 * 管理员
	 */
	ADMIN("admin"),
	/**
	 * 工作人员
	 */
	WORKER("worker"),
	/**
	 * 用户
	 */
	USER("user");
	
	 Role(String name) {
		this.name = name;
	}

	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 
	
}
