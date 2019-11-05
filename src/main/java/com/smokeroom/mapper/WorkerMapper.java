package com.smokeroom.mapper;

import com.smokeroom.entity.Worker;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WorkerMapper {
    int deleteByPrimaryKey(Integer wk_id);

    int insert(Worker record);

    Worker selectByPrimaryKey(Integer wk_id);

    List<Worker> selectAll();

    int updateByPrimaryKey(Worker record);
    
    List<Worker> get ( Worker  worker);
    
}