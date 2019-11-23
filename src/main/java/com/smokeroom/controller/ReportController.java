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
import com.smokeroom.entity.Report;
import com.smokeroom.entity.Task;
import com.smokeroom.mapper.ReportMapper;
import com.smokeroom.mapper.TaskMapper;

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
@Api("巡更报表")
@RestController
@RequestMapping("/report")
public class ReportController extends BaseController{
	@Autowired
	public ReportMapper reportMapper;
	
	@Autowired
	public TaskMapper taskMapper;
	
	/*工作人员权限，小程序端调用*/
	@ApiOperation("提交巡更报表")
	@Permission(role={Role.WORKER})
	@PostMapping("referReport.action")
	public ResultData referReport(Report rep){
		info("提交巡更报表"+rep);
		return quickReturn(reportMapper.insert(rep));
	}
	
	/*
	 * 查看全部/条件查询巡更报表纪录 管理员权限 CMS端调用 小程序端调用
	 * */
	@Permission(role={Role.ADMIN ,Role.WORKER})
	@ApiOperation("查看巡更报表 条件查询/列表查询")
	@GetMapping("getReport.action")
	public ResultData getReport(Report rep){
		info("查看巡更报表"+rep);
		//分页功能
		PageHelper.startPage(rep.getPageNum(), rep.getPageSize());
		return ResultData.success().setData(reportMapper.get(rep));
	}
	
	/*修改巡更报表  管理员权限 CMS端调用*/
	@Permission(role={Role.ADMIN})
	@ApiOperation("修改巡更报表")
	@PostMapping("updateReport.action")
	public ResultData updateReport(Report rep){
		info("修改巡更报表"+rep);
		return quickReturn(reportMapper.updateByPrimaryKey(rep));
	}
	
	/*删除巡更报表  管理员权限 CMS端调用*/
	@Permission(role={Role.ADMIN})
	@ApiOperation("删除巡更报表")
	@PostMapping("deleteReport.action")
	public ResultData deleteReport(Report rep){
		info("删除巡更报表"+rep);
		return quickReturn(reportMapper.deleteByPrimaryKey(rep.getRp_id()));
	}
}
