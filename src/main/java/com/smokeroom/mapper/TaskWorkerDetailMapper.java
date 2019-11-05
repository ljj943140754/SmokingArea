package com.smokeroom.mapper;

import com.smokeroom.entity.TaskWorkerDetail;
import java.util.List;

public interface TaskWorkerDetailMapper {
    int insert(TaskWorkerDetail record);

    List<TaskWorkerDetail> selectAll();
}