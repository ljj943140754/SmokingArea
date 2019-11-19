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
import com.smokeroom.entity.Activity;
import com.smokeroom.mapper.ActivityMapper;

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
@Api("活动管理")
@RestController
@RequestMapping("/activity")
public class ActivityController extends BaseController{
	@Autowired
	private ActivityMapper mapper;
	
	//CMS 调用 小程序端调用
	@ApiOperation("修改活动  启动 关闭")
	@Permission(role={Role.ADMIN,Role.USER})
	@PostMapping("updataAct.action")
	public ResultData updateActivity(Activity act){
		info("修改活动  启动/关闭");
		return quickReturn(mapper.updateByPrimaryKey(act));
	}
	
	//CMS 调用
	@ApiOperation("管理员删除活动管理")
	@Permission(role={Role.ADMIN})
	@PostMapping("deleteAct.action")
	public ResultData deleteActivity(Activity act){
		info("删除活动 ");
		return quickReturn(mapper.deleteByPrimaryKey(act.getAt_id()));
	}
	
	
	/* 用户可以通过  小程序调用
	 * at_id 查看活动详情  
	 * at_uid 查看自己的发布全部活动 
	 * at_status 活动是否开启
	 * 其他字段条件查询
	*/
	@ApiOperation("用户查看自己发布活动")
	@Permission(role={Role.USER})
	@GetMapping("getAct.action")
	public ResultData getActivity (Activity act){
		info("查看己发布活动 ");
		return ResultData.success().setData(mapper.getActivity(act));
	}
	
	/*查看已发布的活动列表 CMS端  小程序端 调用
	 * 已时间倒序的方式查看
	 * */
	@ApiOperation("查看活动列表")
	@Permission(role={Role.ADMIN,Role.USER})
	@GetMapping("getAllAct.action")
	public ResultData getAllActivity(){
		info("查看活动列表 ");
		return ResultData.success().setData(mapper.selectAll());
	}
	/*
	 * 用户发起活动
	 * */
	@ApiOperation("用户发布活动")
	@Permission(role={Role.USER})
	@PostMapping("releaseAct.action")
	public ResultData releaseActivity(Activity act){
		info("用户发起活动");
		return quickReturn(mapper.insert(act));
	}

}
