package com.smokeroom.entity.ext;

import java.util.Date;

import com.smokeroom.entity.Activity;

public class MyActivityVo extends Activity{
	private Integer ji_id;

    private Integer ji_uid;

    private Date ji_creation;

	public Integer getJi_id() {
		return ji_id;
	}

	public void setJi_id(Integer ji_id) {
		this.ji_id = ji_id;
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
		return "MyActivityVo [ji_id=" + ji_id + ", ji_uid=" + ji_uid + ", ji_creation=" + ji_creation + "]";
	}
    
    
}
