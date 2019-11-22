package com.smokeroom.entity;

//任务-人员明细表
public class TaskWorkerDetail {
	private Integer wdt_id;
	
    private Integer wdt_worker_leader;

    private Integer wdt_worker_id;
    
    private String wdt_worker_name;

	public Integer getWdt_id() {
		return wdt_id;
	}

	public void setWdt_id(Integer wdt_id) {
		this.wdt_id = wdt_id;
	}

	public Integer getWdt_worker_leader() {
		return wdt_worker_leader;
	}

	public void setWdt_worker_leader(Integer wdt_worker_leader) {
		this.wdt_worker_leader = wdt_worker_leader;
	}

	public Integer getWdt_worker_id() {
		return wdt_worker_id;
	}

	public void setWdt_worker_id(Integer wdt_worker_id) {
		this.wdt_worker_id = wdt_worker_id;
	}

	public String getWdt_worker_name() {
		return wdt_worker_name;
	}

	public void setWdt_worker_name(String wdt_worker_name) {
		this.wdt_worker_name = wdt_worker_name;
	}

	@Override
	public String toString() {
		return "TaskWorkerDetail [wdt_id=" + wdt_id + ", wdt_worker_leader=" + wdt_worker_leader + ", wdt_worker_id="
				+ wdt_worker_id + ", wdt_worker_name=" + wdt_worker_name + "]";
	}

}