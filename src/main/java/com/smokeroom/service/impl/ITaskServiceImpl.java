package com.smokeroom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.bean.ResultData;
import com.smokeroom.entity.Task;
import com.smokeroom.mapper.TaskMapper;
import com.smokeroom.mapper.TaskRouteDetailMapper;
import com.smokeroom.service.ITaskService;

/**
 * <p>
 *  巡更任务服务实现类
 * </p>
 *
 * @author haiger412
 * @since 2019-10-30
 */
@Service
public class ITaskServiceImpl implements ITaskService {

	@Autowired
	private TaskMapper taskMapper;
	
	@Override
	public ResultData getTask(Task task) {

		return ResultData.success().setData(taskMapper.get(task));
	}

}
