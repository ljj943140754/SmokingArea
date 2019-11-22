package com.smokeroom.entity.ext;

import com.smokeroom.entity.Task;

//封装巡更任务查询 响应小程序访问
public class TaskVO extends Task{
	//领队id(小程序使用)
	private String wk_name;
	//工作人员(小程序使用)
	private String worker_name;
	
	
	public String getWk_name() {
		return wk_name;
	}
	public void setWk_name(String wk_name) {
		this.wk_name = wk_name;
	}
	public String getWorker_name() {
		return worker_name;
	}
	public void setWorker_name(String worker_name) {
		this.worker_name = worker_name;
	}
	@Override
	public String toString() {
		return "TaskVO [wk_name=" + wk_name + ", worker_name=" + worker_name + "]";
	}
	
	

}
