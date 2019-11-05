package com.common.bean;

import java.util.Arrays;

import com.common.enu.Role;
import com.smokeroom.entity.User;
import com.smokeroom.entity.Worker;

/**
 * 统一的用户类。
 * @author Administrator
 *
 */    
public class CommonUser {
	private Role []roles;//admin  管理员、user用户、worker 员工
    private User user;
    private Worker worker;
	 
	 
	public Role[] getRoles() {
		return roles;
	}
	public void setRoles(Role[] roles) {
		this.roles = roles;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	@Override
	public String toString() {
		return "CommonUser [roles=" + Arrays.toString(roles) + ", user=" + user + ", worker=" + worker + "]";
	}
	 
	
	
    
}
