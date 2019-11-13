package com.smokeroom.mapper;

import com.smokeroom.entity.ActivityJoinIn;
import java.util.List;
 
public interface ActivityJoinInMapper {
    int insert(ActivityJoinIn record);

    List<ActivityJoinIn> selectAll();
    
    //检查是否已报名
    ActivityJoinIn checkSign(ActivityJoinIn actJo);
    
    //获取当前用户已报名的活动
    List<ActivityJoinIn> getSignActivity(ActivityJoinIn actJo);
}