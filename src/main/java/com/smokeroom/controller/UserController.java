package com.smokeroom.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ano.Permission;
import com.common.bean.CommonUser;
import com.common.bean.ResultData;
import com.common.controller.BaseController;
import com.common.enu.HttpSessionKey;
import com.common.enu.Role;
import com.smokeroom.entity.User;

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
@Api("用户接口")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@ApiOperation("登陆接口")
	@PostMapping("login.action")
	public ResultData login(User user) {
		 
		return ResultData.success("OK");
	}
	
	@ApiOperation("测试假登陆接口")
	@PostMapping("testlogin.action")
	public ResultData login(HttpServletRequest rq ) {
		rq.getSession();
		
		return ResultData.success("OK");
	}
	
	@ApiOperation("测试真实登陆接口")
	@PostMapping("reallogin.action")
	public ResultData login2(HttpServletRequest rq ,String  role) {
		CommonUser cmu = new CommonUser();
		if("admin".equals( role )) {
			cmu.setRole(Role.ADMIN);
		}else if("user".equals( role )) {
			cmu.setRole(Role.USER);
		}else if("worker".equals( role )) {
			cmu.setRole(Role.WORKER);
		}
		rq.getSession().setAttribute(HttpSessionKey.USER_SESSION_KEY.getCode(), cmu);
		return ResultData.success("已经登陆");
	}
	
	@ApiOperation("测试用户权限接口")
	@PostMapping("saveuser.action")
	@Permission(role=Role.USER)
	public ResultData save( ) {
		return ResultData.success("访问用户接口成功");
	}
	
	@ApiOperation("测试管理员权限接口")
	@PostMapping("saveadmin.action")
	@Permission(role=Role.ADMIN)
	public ResultData save2( ) {
		return ResultData.success("访问管理员接口成功");
	}
	
	@ApiOperation("测试工作人员权限接口")
	@PostMapping("saveworker.action")
	@Permission(role=Role.WORKER)
	public ResultData save3( ) {
		return ResultData.success("访问工作人员接口成功");
	}
	
	
}
