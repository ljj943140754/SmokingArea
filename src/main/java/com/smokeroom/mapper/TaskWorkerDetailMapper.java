package com.smokeroom.mapper;

import java.util.List;

import com.smokeroom.entity.Task;
import com.smokeroom.entity.TaskWorkerDetail;
import com.smokeroom.entity.ext.TaskWorkerDetailVO;

public interface TaskWorkerDetailMapper {
    int insert(TaskWorkerDetail record);
    
    int delete(TaskWorkerDetail record);

    List<TaskWorkerDetail> selectAll();
    
    List<TaskWorkerDetailVO> getTaskWorker(Task task);
}