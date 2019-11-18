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
import com.smokeroom.entity.Trace;
import com.smokeroom.mapper.TraceMapper;

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
	
	
	@ApiOperation("扫码插入我的痕迹")
	@Permission(role={Role.USER})
	@PostMapping("insertTrace.action")
	public ResultData insertTrace(Trace trace) {
		info("查看我的痕迹"+trace);
		return quickReturn(mapper.insert(trace));
	}
	
	/*
	 * 用户查看我的痕迹 小程序端调用 用户权限
	 * */
	@ApiOperation("扫码插入我的痕迹")
	@Permission(role={Role.USER})
	@GetMapping("selectMyTrace.action")
	public ResultData selectMyTrace(Trace trace) {
		info("查看我的痕迹"+trace);
		return quickReturn(mapper.insert(trace));
	}

}
