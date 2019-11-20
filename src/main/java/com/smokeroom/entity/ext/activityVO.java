package com.smokeroom.entity.ext;

import com.smokeroom.entity.Activity;

/*
 * 活动返回封装对象VO*/
public class activityVO extends Activity{
	//封装 group_cancat 数据
	private String userData;
	
	

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	@Override
	public String toString() {
		return "activityVO [userData=" + userData + "]";
	}
	

	
}
