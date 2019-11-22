package com.smokeroom.entity.ext;

/*业务对象
 * 封装员工扫码签到巡更任务 业务数据BO*/
public class signInBO {
	//领队id 
    private Integer wdt_worker_leader;
    //工作人员id
    private Integer wdt_worker_id;
    //任务id
    private Integer sin_tsk_id;
    //设施id 
    private Integer sin_fac_id;
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
	@Override
	public String toString() {
		return "signInBO [wdt_worker_leader=" + wdt_worker_leader + ", wdt_worker_id=" + wdt_worker_id + ", sin_tsk_id="
				+ sin_tsk_id + ", sin_fac_id=" + sin_fac_id + "]";
	}
}
