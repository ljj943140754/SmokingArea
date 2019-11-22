package com.smokeroom.mapper;

import java.util.List;

import com.smokeroom.entity.Task;
import com.smokeroom.entity.TaskRecord;
import com.smokeroom.entity.taskProcgress;

public interface TaskRecordMapper {
	
	//关联 巡更表查询出是否完成当天巡更任务
	 List<Task> getIsFinishedTask(taskProcgress procgress);
	 
	 //插入当前巡更签到纪录
	 int insert (TaskRecord rec);
	 
	 //查看所有未签到列表
	 List<TaskRecord> selectAll();
}
