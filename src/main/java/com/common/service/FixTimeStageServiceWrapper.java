package com.common.service;

public class FixTimeStageServiceWrapper {
	private FixTimeStageService  serviceInstance;
	private int start_h;
	private int start_min;
	private boolean executedoay;//今天是否执行。
	
	
	
	public boolean isExecutedoay() {
		return executedoay;
	}
	public void setExecutedoay(boolean executedoay) {
		this.executedoay = executedoay;
	}
	public FixTimeStageService getServiceInstance() {
		return serviceInstance;
	}
	public void setServiceInstance(FixTimeStageService serviceInstance) {
		this.serviceInstance = serviceInstance;
	}
	public int getStart_h() {
		return start_h;
	}
	public void setStart_h(int start_h) {
		this.start_h = start_h;
	}
	public int getStart_min() {
		return start_min;
	}
	public void setStart_min(int start_min) {
		this.start_min = start_min;
	}
	 
	
}
