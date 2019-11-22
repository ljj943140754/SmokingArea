package com.smokeroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ano.Permission;
import com.common.bean.ResultData;
import com.common.controller.BaseController;
import com.common.enu.Role;
import com.smokeroom.mapper.TaskRecordMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 巡更任务签到纪录管理*/
@Api("巡更任务签到纪录管理")
@RestController
@RequestMapping("/taskrecord")
public class TaskRecordController extends BaseController {
	@Autowired
	private TaskRecordMapper taskRecordMapper;

	// 查看巡更任务 CMS端调用
	@ApiOperation("查询所有巡更任务签到纪录")
	@Permission(role = { Role.ADMIN })
	@GetMapping("getTaskRecord.action")
	public ResultData getTaskRecord() {
		info("条件查询巡更任务" );
		return ResultData.success().setData(taskRecordMapper.selectAll());
	}

}
