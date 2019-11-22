package com.smokeroom.entity;

import java.util.Date;

/*任务巡更签到纪录表  方便管理员查看那个小队缺勤*/
public class TaskRecord {
	private Integer tr_id;
	private Integer tr_task_id;
	private Integer tr_worker_leader;
	private Integer tr_iscomplete;
	private Date tr_creation;
	public Integer getTr_id() {
		return tr_id;
	}
	public void setTr_id(Integer tr_id) {
		this.tr_id = tr_id;
	}
	public Integer getTr_task_id() {
		return tr_task_id;
	}
	public void setTr_task_id(Integer tr_task_id) {
		this.tr_task_id = tr_task_id;
	}
	public Integer getTr_worker_leader() {
		return tr_worker_leader;
	}
	public void setTr_worker_leader(Integer tr_worker_leader) {
		this.tr_worker_leader = tr_worker_leader;
	}
	public Integer getTr_iscomplete() {
		return tr_iscomplete;
	}
	public void setTr_iscomplete(Integer tr_iscomplete) {
		this.tr_iscomplete = tr_iscomplete;
	}
	public Date getTr_creation() {
		return tr_creation;
	}
	public void setTr_creation(Date tr_creation) {
		this.tr_creation = tr_creation;
	}
	@Override
	public String toString() {
		return "TaskRecord [tr_id=" + tr_id + ", tr_task_id=" + tr_task_id + ", tr_worker_leader=" + tr_worker_leader
				+ ", tr_iscomplete=" + tr_iscomplete + ", tr_creation=" + tr_creation + "]";
	}
	
	
}
