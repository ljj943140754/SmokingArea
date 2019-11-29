package com.smokeroom.mapper;

import java.util.List;

import com.smokeroom.entity.Activity;
import com.smokeroom.entity.ext.activityVO;

public interface ActivityMapper {
    int deleteByPrimaryKey(Integer at_id);

    int insert(Activity record);

    Activity selectByPrimaryKey(Integer at_id);

    List<Activity> selectAll();

    int updateByPrimaryKey(Activity record);
    
    /*List<Activity> getActivity(Activity record);*/
    List<Activity> getActivity(Activity record);
    
    int updateSignCount(Activity act);
}