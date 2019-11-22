package com.smokeroom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ano.Permission;
import com.common.bean.ResultData;
import com.common.controller.BaseController;
import com.common.enu.Role;
import com.smokeroom.entity.Task;
import com.smokeroom.entity.ext.TaskRouteDetailVO;
import com.smokeroom.mapper.TaskRouteDetailMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author haiger412
 * @since 2019-10-30
 */
@Api("巡更路线管理")
@RestController
@RequestMapping("/task-route-detail")
public class TaskRouteDetailController extends BaseController{
	@Autowired
	private TaskRouteDetailMapper taskRouteDetailMapper;
	
	/*
	 * 获取巡更路线，CMS 端调用  按路线指定顺序排列*/
	@ApiOperation("获取巡更路线 ")
	@Permission(role={Role.ADMIN})
	@GetMapping("getRouteDetail.action")
	public ResultData routeDetail(Task task){
		info("查看巡更任务测试 "+task);
		return ResultData.success().setData(taskRouteDetailMapper.get(task));
	}
	
	/*
	 * 添加巡更路线 CMS 端调用 管理员权限 */
	@ApiOperation("添加巡更路线")
	@Permission(role={Role.ADMIN})
	@PostMapping("addTaskRoute.action")
	public ResultData addTaskRoute(TaskRouteDetailVO route){
		if(route.getRdt_tsk_seq()==null||route.getRdt_tsk_seq().equals("")){
			return ResultData.fail("路线顺序不能为空");
		}
		return ResultData.success().setData(taskRouteDetailMapper.insert(route));
	}
	
	/*删除巡更路线 CMS 端调用 管理员权限*/
	@ApiOperation("删除巡更路线")
	@Permission(role={Role.ADMIN})
	@PostMapping("delTaskRoute.action")
	public ResultData delTaskRoute(TaskRouteDetailVO route){
		info("删除巡更路线"+route);
		return ResultData.success().setData(taskRouteDetailMapper.delete(route));
	}
	
}
