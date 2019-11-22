package com.smokeroom.entity;

import java.util.Date;

public class taskProcgress {
	private Integer tp_id;
	private Integer tp_task_id;
	private Integer tp_wk_id;
	private Integer tp_status;
	private Date tp_start_time;
	private Date tp_finish_time;
	public Integer getTp_id() {
		return tp_id;
	}
	public void setTp_id(Integer tp_id) {
		this.tp_id = tp_id;
	}
	public Integer getTp_task_id() {
		return tp_task_id;
	}
	public void setTp_task_id(Integer tp_task_id) {
		this.tp_task_id = tp_task_id;
	}
	public Integer getTp_wk_id() {
		return tp_wk_id;
	}
	public void setTp_wk_id(Integer tp_wk_id) {
		this.tp_wk_id = tp_wk_id;
	}
	public Integer getTp_status() {
		return tp_status;
	}
	public void setTp_status(Integer tp_status) {
		this.tp_status = tp_status;
	}
	public Date getTp_start_time() {
		return tp_start_time;
	}
	public void setTp_start_time(Date tp_start_time) {
		this.tp_start_time = tp_start_time;
	}
	public Date getTp_finish_time() {
		return tp_finish_time;
	}
	public void setTp_finish_time(Date tp_finish_time) {
		this.tp_finish_time = tp_finish_time;
	}
	@Override
	public String toString() {
		return "taskProcgress [tp_id=" + tp_id + ", tp_task_id=" + tp_task_id + ", tp_wk_id=" + tp_wk_id
				+ ", tp_status=" + tp_status + ", tp_start_time=" + tp_start_time + ", tp_finish_time=" + tp_finish_time
				+ "]";
	}

}
