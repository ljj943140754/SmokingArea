package com.smokeroom.entity;

//任务-路线明细表
public class TaskRouteDetail {
    private Integer rdt_tsk_id;

    private Integer rdt_fac_id;

    private Integer rdt_tsk_seq;

    public Integer getRdt_tsk_id() {
        return rdt_tsk_id;
    }

    public void setRdt_tsk_id(Integer rdt_tsk_id) {
        this.rdt_tsk_id = rdt_tsk_id;
    }

    public Integer getRdt_fac_id() {
        return rdt_fac_id;
    }

    public void setRdt_fac_id(Integer rdt_fac_id) {
        this.rdt_fac_id = rdt_fac_id;
    }

    public Integer getRdt_tsk_seq() {
        return rdt_tsk_seq;
    }

    public void setRdt_tsk_seq(Integer rdt_tsk_seq) {
        this.rdt_tsk_seq = rdt_tsk_seq;
    }

	@Override
	public String toString() {
		return "TaskRouteDetail [rdt_tsk_id=" + rdt_tsk_id + ", rdt_fac_id=" + rdt_fac_id + ", rdt_tsk_seq="
				+ rdt_tsk_seq + "]";
	}
    
    
}