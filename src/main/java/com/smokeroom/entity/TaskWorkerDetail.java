package com.smokeroom.entity;

//任务-人员明细表
public class TaskWorkerDetail {
    private Integer wdt_tsk_id;

    private Integer wdt_worker_id;

    public Integer getWdt_tsk_id() {
        return wdt_tsk_id;
    }

    public void setWdt_tsk_id(Integer wdt_tsk_id) {
        this.wdt_tsk_id = wdt_tsk_id;
    }

    public Integer getWdt_worker_id() {
        return wdt_worker_id;
    }

    public void setWdt_worker_id(Integer wdt_worker_id) {
        this.wdt_worker_id = wdt_worker_id;
    }

	@Override
	public String toString() {
		return "TaskWorkerDetail [wdt_tsk_id=" + wdt_tsk_id + ", wdt_worker_id=" + wdt_worker_id + "]";
	}
    
}