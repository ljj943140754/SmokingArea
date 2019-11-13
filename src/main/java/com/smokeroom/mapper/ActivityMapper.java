package com.smokeroom.mapper;

import com.smokeroom.entity.Activity;
import java.util.List;

public interface ActivityMapper {
    int deleteByPrimaryKey(Integer at_id);

    int insert(Activity record);

    Activity selectByPrimaryKey(Integer at_id);

    List<Activity> selectAll();

    int updateByPrimaryKey(Activity record);
    
    List<Activity> getActivity(Activity record);
    
    int updateSignCount(Activity act);
}