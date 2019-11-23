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
import com.smokeroom.entity.Task;
import com.smokeroom.mapper.TaskMapper;
import com.smokeroom.service.ITaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 * 巡更任务控制器
 * 
 * @author haiger412
 * @since 2019-10-30
 */
@Api("巡更管理")
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {
	@Autowired
	private TaskMapper mapper;

	@Autowired
	private ITaskService taskService;

	// 管理员添加巡更任务 管理员权限
	@ApiOperation("添加巡更任务")
	@Permission(role = { Role.ADMIN })
	@PostMapping("insert.action")
	public ResultData addTask(Task task) {
		info("添加巡更任务" + task);
		return quickReturn(mapper.insert(task));
	}

	// 管理员删除巡更任务 管理员权限
	@ApiOperation("删除巡更任务")
	@Permission(role = { Role.ADMIN })
	@PostMapping("delete.action")
	public ResultData delTask(Task task) {
		info("删除巡更任务" + task);
		return quickReturn(mapper.deleteByPrimaryKey(task.getTsk_id()));
	}

	// 管理员修改巡更任务 管理员权限
	@ApiOperation("修改巡更任务")
	@Permission(role = { Role.ADMIN })
	@PostMapping("update.action")
	public ResultData updataTask(Task task) {
		info("修改巡更任务" + task);
		return quickReturn(mapper.updateByPrimaryKey(task));
	}

	// 查看巡更任务 CMS端调用
	@ApiOperation("查看巡更任务 条件查询/列表查询 CMS调用 ")
	@Permission(role = { Role.ADMIN })
	@GetMapping("getTask.action")
	public ResultData getTask(Task task) {
		info("条件查询巡更任务" + task);
		//分页功能
		PageHelper.startPage(task.getPageNum(), task.getPageSize());
		return ResultData.success().setData(mapper.selectTask(task));
	}

	// 查看巡更任务 小程序端调用
	@ApiOperation("查看巡更任务 条件查询/列表查询 一对多查询 ")
	@Permission(role = { Role.WORKER })
	@GetMapping("findTask.action")
	public ResultData findTask(Task task) {
		info("条件查询巡更任务" + task);
		//分页功能
		//PageHelper.startPage(task.getPageNum(), task.getPageSize());
		return ResultData.success().setData(mapper.get(task));
	}

}
