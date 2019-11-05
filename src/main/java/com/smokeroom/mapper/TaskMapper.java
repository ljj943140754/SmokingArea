package com.smokeroom.mapper;

import com.smokeroom.entity.Task;
import java.util.List;

public interface TaskMapper {
    int deleteByPrimaryKey(Integer tsk_id);

    int insert(Task record);

    Task selectByPrimaryKey(Integer tsk_id);

    List<Task> selectAll();

    int updateByPrimaryKey(Task record);
}