package com.smokeroom.service;

import com.common.bean.ResultData;
import com.smokeroom.entity.Task;

/**
 * <p>
 *  巡更任务服务类
 * </p>
 *
 * @author haiger412
 * @since 2019-10-30
 */
public interface ITaskService{

	
	//查询发布的巡更任务
	ResultData getTask(Task task);
	
}
