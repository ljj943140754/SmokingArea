package com.smokeroom.mapper;

import com.smokeroom.entity.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer ur_id);

    int insert(User record);

    User selectByPrimaryKey(Integer ur_id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}