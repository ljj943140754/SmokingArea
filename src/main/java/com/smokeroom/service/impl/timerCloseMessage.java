package com.smokeroom.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.ano.TimeStage;
import com.common.service.FixTimeStageService;
import com.smokeroom.entity.Message;
import com.smokeroom.mapper.MessageMapper;

@Service
@TimeStage("23:59")
public class timerCloseMessage implements FixTimeStageService{
	@Autowired
	private MessageMapper mapper;
	
	@Override
	public void execute() {
		//1、当前的时间
		Date d = new Date();
		//2、封装 Message 对象，设置状态字段和当前时间
		Message mes =new Message();
		mes.setMsg_status(0);
		mes.setMsg_creation(d);
		/*3、传入当前时间 和 状态字段，mybatis 使用 datediff 函数将当前23:59
			发出的消息通知修改，实现定时删除通知功能。
		*/
		mapper.closeMessage(mes);
	
	}

}