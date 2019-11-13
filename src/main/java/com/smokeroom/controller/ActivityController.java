package com.smokeroom.controller;


import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@ApiOperation("修改活动  启动 关闭")
	@Permission(role={Role.ADMIN,Role.USER})
	@PostMapping("updataAct.action")
	public ResultData updateActivity(Activity act){
		info("修改活动  启动/关闭");
		return quickReturn(mapper.updateByPrimaryKey(act));
	}
	
	@ApiOperation("管理员删除活动管理")
	@Permission(role={Role.ADMIN})
	@PostMapping("deleteAct.action")
	public ResultData deleteActivity(Activity act){
		info("删除活动 ");
		return quickReturn(mapper.deleteByPrimaryKey(act.getAt_id()));
	}
	
	

}
