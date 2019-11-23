package com.smokeroom.entity;

import java.util.Date;
//巡更-签到表
public class SignInRecord extends pageObject{
	
	private Integer sin_id;
	
    private Integer sin_tsk_id;

    private Integer sin_fac_id;

    private Integer sin_worker_id;
    
    private Integer sin_prog_id;

    private Date sin_creation;

	public Integer getSin_id() {
		return sin_id;
	}

	public void setSin_id(Integer sin_id) {
		this.sin_id = sin_id;
	}

	public Integer getSin_tsk_id() {
		return sin_tsk_id;
	}

	public void setSin_tsk_id(Integer sin_tsk_id) {
		this.sin_tsk_id = sin_tsk_id;
	}

	public Integer getSin_fac_id() {
		return sin_fac_id;
	}

	public void setSin_fac_id(Integer sin_fac_id) {
		this.sin_fac_id = sin_fac_id;
	}

	public Integer getSin_worker_id() {
		return sin_worker_id;
	}

	public void setSin_worker_id(Integer sin_worker_id) {
		this.sin_worker_id = sin_worker_id;
	}

	public Integer getSin_prog_id() {
		return sin_prog_id;
	}

	public void setSin_prog_id(Integer sin_prog_id) {
		this.sin_prog_id = sin_prog_id;
	}

	public Date getSin_creation() {
		return sin_creation;
	}

	public void setSin_creation(Date sin_creation) {
		this.sin_creation = sin_creation;
	}

	@Override
	public String toString() {
		return "SignInRecord [sin_id=" + sin_id + ", sin_tsk_id=" + sin_tsk_id + ", sin_fac_id=" + sin_fac_id
				+ ", sin_worker_id=" + sin_worker_id + ", sin_prog_id=" + sin_prog_id + ", sin_creation=" + sin_creation
				+ "]";
	}
  
}