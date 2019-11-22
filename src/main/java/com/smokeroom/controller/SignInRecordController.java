package com.smokeroom.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ano.Permission;
import com.common.bean.ResultData;
import com.common.controller.BaseController;
import com.common.enu.Role;
import com.smokeroom.entity.taskProcgress;
import com.smokeroom.entity.ext.TaskWorkerDetailVO;
import com.smokeroom.entity.ext.signInBO;
import com.smokeroom.mapper.SignInRecordMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  签到管理前端控制器
 * </p>
 *
 * @author haiger412
 * @since 2019-10-30
 * 
 */
@Api("签到管理")
@RestController
@RequestMapping("/sign-in-record")
public class SignInRecordController extends BaseController{
	
	@Autowired
	private SignInRecordMapper signInRecordMapper;
	
	/*
	 * 工作人员扫码签到*/
	@ApiOperation("巡更任务扫码签到")
	@Permission(role={Role.WORKER})
	@PostMapping("sweepSign.action")
	public ResultData sweepSign(signInBO BO){
		info("巡更任务扫码签到"+BO);
		int signIn = signInRecordMapper.updateSignIn(BO);
		if(signIn == 0){
			return ResultData.success("签到成功");
		}
		return ResultData.fail("您不是该任务的巡更人员，不能扫码！");
	}
	
	
	@ApiOperation("测试使用")
	@PostMapping("Test.action")
	public ResultData Test(){
		return null;
	}
	

}
