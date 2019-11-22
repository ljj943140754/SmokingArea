package com.smokeroom.entity.ext;

import com.smokeroom.entity.TaskRouteDetail;

/*
 * 封装巡更路线，CMS 响应数据*/
public class TaskRouteDetailVO extends TaskRouteDetail{
	
	private String fy_name;

	public String getFy_name() {
		return fy_name;
	}

	public void setFy_name(String fy_name) {
		this.fy_name = fy_name;
	}

	@Override
	public String toString() {
		return "route_DetailVO [fy_name=" + fy_name + "]";
	}

}
