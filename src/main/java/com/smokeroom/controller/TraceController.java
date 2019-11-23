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
import com.smokeroom.entity.Trace;
import com.smokeroom.entity.ext.userTraceVO;
import com.smokeroom.mapper.TraceMapper;
import com.smokeroom.mapper.UserMapper;
import com.smokeroom.service.ITraceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author haiger412
 * @since 2019-10-30
 */
@Api("查看痕迹")
@RestController
@RequestMapping("/trace")
public class TraceController extends BaseController {
	@Autowired
	private TraceMapper mapper;
	
	@Autowired
	private ITraceService service;
	
	@Autowired
	private UserMapper userMapper;
	
	/*
	 * 用户扫码添加痕迹纪录
	 * */
	@ApiOperation("手动扫码插入我的痕迹")
	@Permission(role={Role.USER})
	@PostMapping("insertTrace.action")
	public ResultData insertTrace(Trace trace) {
		info("手动插入我的痕迹"+trace);
		//更新积分这里有个小 bug 只要扫码就更新积分 
		userMapper.updateScores(trace.getTe_u_id());
		return quickReturn(mapper.insert(trace));
	}
	
	/*
	 * 用户每次打开小程序添加痕迹纪录
	 * */
	@ApiOperation("用户打开小程序更新痕迹位置")
	@Permission(role = {Role.USER})
	@PostMapping("updateTrace.action")
	public ResultData updateTrace( userTraceVO utVO ){
		return service.updateUserTrace(utVO);
	}
	
	/*
	 * 用户查看我的痕迹 小程序端调用 用户权限
	 * */
	@ApiOperation("用户查看我的痕迹")
	@Permission(role={Role.USER})
	@GetMapping("selectMyTrace.action")
	public ResultData selectMyTrace(Trace trace) {
		info("用户查看我的痕迹"+trace);
		//分页功能
		//PageHelper.startPage(trace.getPageNum(), trace.getPageSize());
		return ResultData.success().setData(mapper.get(trace));
	}

}
