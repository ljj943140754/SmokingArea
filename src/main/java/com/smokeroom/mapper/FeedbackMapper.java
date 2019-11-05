package com.smokeroom.mapper;

import com.smokeroom.entity.Feedback;
import java.util.List;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer fb_id);

    int insert(Feedback record);

    List<Feedback> selectByPrimaryKey(Feedback record);

    List<Feedback> selectAll();

    int updateByPrimaryKey(Feedback record);
    
}