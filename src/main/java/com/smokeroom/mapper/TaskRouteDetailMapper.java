package com.smokeroom.mapper;

import java.util.List;

import com.smokeroom.entity.Task;
import com.smokeroom.entity.TaskRouteDetail;
import com.smokeroom.entity.ext.TaskRouteDetailVO;

public interface TaskRouteDetailMapper {
	
    int insert(TaskRouteDetailVO record);

    List<TaskRouteDetail> selectAll();
    
    List<TaskRouteDetailVO> get(Task task);
    
    int delete(TaskRouteDetailVO record);
}