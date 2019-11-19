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
import com.smokeroom.entity.Message;
import com.smokeroom.mapper.MessageMapper;
import com.smokeroom.service.impl.timerCloseMessage;

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
@Api("消息接口")
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController {
    @Autowired
	private MessageMapper mapper;
    
    @Autowired
    private timerCloseMessage m;
    
	@ApiOperation("管理员发布消息。需要管理员权限才能发布。")
	@PostMapping("insert.action")
	@Permission(role = {Role.ADMIN })
	public ResultData insert(Message msg) {
		info("插入数据" + msg);
		return quickReturn(mapper.insert(msg));
	}
	
	@ApiOperation("管理员更新消息。需要管理员权限才能更新。")
	@PostMapping("update.action")
	@Permission(role = {Role.ADMIN })
	public ResultData update(Message msg) {
		info("更新" + msg);
		return quickReturn(mapper.updateByPrimaryKey(msg));
	}
	
	
	@ApiOperation("测试使用")
	@GetMapping("test.action")
	public ResultData Test(){
		m.execute();
		return ResultData.success();
	}
	
	/*
	 * CMS 端调用可以条件查询，列表查询
	 * 小程序端调用 参数 msg_status = 1 查看开启的消息
	 * */
	@ApiOperation("按条件查询消息。适用列表查询、条件查询")
	@GetMapping("get.action")
	public ResultData get( Message query ) {
		info("查询消息：" + query);
		return ResultData.success().setData( mapper.get(query));
	}
}
