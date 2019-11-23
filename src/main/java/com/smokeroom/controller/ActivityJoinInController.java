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
import com.github.pagehelper.PageHelper;
import com.smokeroom.entity.ActivityJoinIn;
import com.smokeroom.mapper.ActivityJoinInMapper;
import com.smokeroom.service.IActivityJoinInService;

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
@Api("活动报名管理")
@RestController
@RequestMapping("/activity-join-in")
public class ActivityJoinInController extends BaseController{
	@Autowired
	private ActivityJoinInMapper mapper;
	
	@Autowired
	private IActivityJoinInService server;
	
	/*
	 * 用户报名活动  小程序端调用
	 * */
	@ApiOperation("用户报名活动")
	@Permission(role={Role.USER})
	@PostMapping("signActivity.action")
	public ResultData signActivity(ActivityJoinIn actJo){
		info("活动报名");
		return server.activityPocessing(actJo);
	}
	
	/*
	 * 查看我的报名
	 * 封装了 实体类 MyActivityVO 继承于Activity 定义链接查询需要的字段
	 * */
	@ApiOperation("查看我的报名")
	@Permission(role={Role.USER})
	@GetMapping("getMyActivity.action")
	public ResultData getMyActivity(ActivityJoinIn actJo){
		info("查看我的报名");
		//分页功能
		PageHelper.startPage(actJo.getPageNum(), actJo.getPageSize());
		return ResultData.success().setData(mapper.getSignActivity(actJo));
	}
	

}
