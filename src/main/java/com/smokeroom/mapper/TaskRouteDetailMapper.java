package com.smokeroom.mapper;

import com.smokeroom.entity.TaskRouteDetail;
import java.util.List;

public interface TaskRouteDetailMapper {
    int insert(TaskRouteDetail record);

    List<TaskRouteDetail> selectAll();
}