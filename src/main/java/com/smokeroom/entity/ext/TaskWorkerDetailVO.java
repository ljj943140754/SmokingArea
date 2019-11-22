package com.smokeroom.entity.ext;

import com.smokeroom.entity.TaskWorkerDetail;

/*
 * 封装巡更人员详情，CMS 响应数据*/
public class TaskWorkerDetailVO extends TaskWorkerDetail{
	
	//巡更人员手机号
	private String wk_phone;

	public String getWk_phone() {
		return wk_phone;
	}

	public void setWk_phone(String wk_phone) {
		this.wk_phone = wk_phone;
	}

	@Override
	public String toString() {
		return "worker_DetailVO [wk_phone=" + wk_phone + "]";
	}

	
}
