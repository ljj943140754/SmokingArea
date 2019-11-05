package com.smokeroom.mapper;

import com.smokeroom.entity.Message;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer msg_id);

    int insert(Message record);

    Message selectByPrimaryKey(Integer msg_id);

    List<Message> get(Message query);

    int updateByPrimaryKey(Message record);
}