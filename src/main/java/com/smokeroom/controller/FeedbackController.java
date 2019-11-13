package com.smokeroom.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.common.ano.Permission;
import com.common.bean.ResultData;
import com.common.controller.BaseController;
import com.common.enu.Role;
import com.smokeroom.entity.Feedback;
import com.smokeroom.mapper.FeedbackMapper;

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
@Api("用户反馈信息")
@RestController
@RequestMapping("/feedback")
public class FeedbackController extends BaseController{
	@Autowired
	private FeedbackMapper mapper;
	
	//小程序端调用
	@ApiOperation("用户反馈")
	@PostMapping("userFeedback.action")
	public ResultData userFeedback(Feedback fee) {
		info("用户反馈："+fee);
		return quickReturn(mapper.insert(fee));
	}
	
	//CMS 端调用
	@Permission(role = {Role.ADMIN})
	@ApiOperation("查看用户反馈")
	@GetMapping("selectAll.action")
	public ResultData selectAll(Feedback fee){
		info("查看用户反馈");
		return ResultData.success().setData(mapper.get(fee));
	}
	
	//CMS 端调用
	@Permission(role = { Role.ADMIN })
	@ApiOperation("处理用户意见反馈")
	@PostMapping("userHandle.action")
	public ResultData userHandle(Feedback fee) {
		info("处理用户意见反馈：");
		return quickReturn(mapper.updateByPrimaryKey(fee));
	}
	
	//小程序端调用
	@Permission(role = { Role.USER })
	@ApiOperation("用户查看自己反馈记录")
	@GetMapping("userRecord.action")
	public ResultData userRecord(Feedback fee) {
		info("用户查看意见记录：");
		return ResultData.success().setData(mapper.selectByPrimaryKey(fee));
	}
	
	

}
