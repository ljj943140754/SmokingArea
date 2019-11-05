package com.smokeroom.mapper;

import com.smokeroom.entity.ActivityJoinIn;
import java.util.List;
 
public interface ActivityJoinInMapper {
    int insert(ActivityJoinIn record);

    List<ActivityJoinIn> selectAll();
}