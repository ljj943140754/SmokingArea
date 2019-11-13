package com.smokeroom.entity.ext;

import com.smokeroom.entity.Feedback;

public class FeedbackVO extends Feedback{
	
	private String ur_nickname;	
	private String ur_avatarurl;
	public String getUr_nickname() {
		return ur_nickname;
	}
	public void setUr_nickname(String ur_nickname) {
		this.ur_nickname = ur_nickname;
	}
	public String getUr_avatarurl() {
		return ur_avatarurl;
	}
	public void setUr_avatarurl(String ur_avatarurl) {
		this.ur_avatarurl = ur_avatarurl;
	}
	
}
