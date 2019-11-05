package com.smokeroom.service.impl;

import org.springframework.stereotype.Service;

import com.common.ano.TimeStage;
import com.common.service.FixTimeStageService;

@Service
@TimeStage("23:59")
public class Time11111 implements FixTimeStageService{

	@Override
	public void execute() {
		System.out.println("执行咯。。。");
	}

}
