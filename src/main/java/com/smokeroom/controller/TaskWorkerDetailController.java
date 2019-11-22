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
import com.smokeroom.entity.ext.TaskWorkerDetailVO;
import com.smokeroom.mapper.TaskWorkerDetailMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  人员明细管理前端控制器
 * </p>
 *
 * @author haiger412
 * @since 2019-10-30
 */
@Api("人员明细管理")
@RestController
@RequestMapping("/task-worker-detail")
public class TaskWorkerDetailController extends BaseController{
	@Autowired
	private TaskWorkerDetailMapper taskWorkerDetailMapper;
	
	/*
	 * 获取人员明细 CMS调用*/
	@ApiOperation("获取巡更人员明细")
	@Permission(role={Role.ADMIN})
	@GetMapping("getWorkerDetail.action")
	public ResultData getWorkerDetail(Task task){
		info("获取人员明细:"+task);
		return ResultData.success().setData(taskWorkerDetailMapper.getTaskWorker(task));
	}
	
	/*添加巡更人员明细 CMS 调用*/
	@ApiOperation("添加巡更人员明细 ")
	@Permission(role={Role.ADMIN})
	@PostMapping("addTaskWorker.action")
	public ResultData addTaskWorker(TaskWorkerDetailVO worker){
		info("添加巡更人员明细"+worker);
		return quickReturn(taskWorkerDetailMapper.insert(worker));
	}
	
	/*删除巡更人员明细 CMS 调用*/
	@ApiOperation("删除巡更人员明细 ")
	@Permission(role={Role.ADMIN})
	@PostMapping("delTaskWorker.action")
	public ResultData delTaskWorker(TaskWorkerDetailVO worker){
		info("删除巡更人员明细"+worker);
		return quickReturn(taskWorkerDetailMapper.delete(worker));
	}

}
