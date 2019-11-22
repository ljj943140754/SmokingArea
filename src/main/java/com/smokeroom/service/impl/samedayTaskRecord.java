package com.smokeroom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.ano.TimeStage;
import com.common.service.FixTimeStageService;
import com.smokeroom.entity.Task;
import com.smokeroom.entity.TaskRecord;
import com.smokeroom.entity.taskProcgress;
import com.smokeroom.mapper.TaskRecordMapper;
/*定时添加巡更签到纪录，方便管理员查看那个小队缺勤*/
@Service
@TimeStage("20:33")
public class samedayTaskRecord implements FixTimeStageService{

	@Autowired
	private TaskRecordMapper taskRecordMapper;
	
	@Override
	public void execute() {
		//创建任务进度对象
		taskProcgress pro = new taskProcgress();
		//设置进度对象 Tp_status 状态 0 未完成 
		pro.setTp_status(0);
		List<Task> list = taskRecordMapper.getIsFinishedTask( pro );
		//创建签到纪录对象 为其赋值，值是从 Task 关联 进度表查询结果
		TaskRecord record = new TaskRecord();
		for (Task task : list) {
			record.setTr_iscomplete(pro.getTp_status());
			record.setTr_task_id(task.getTsk_id());
			record.setTr_worker_leader(task.getTsk_leader_id());
			taskRecordMapper.insert(record);
		}
	}

}
