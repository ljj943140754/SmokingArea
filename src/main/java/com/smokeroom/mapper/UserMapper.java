package com.smokeroom.mapper;

import com.smokeroom.entity.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer ur_id);

    int insert(User record);
    
    User selectByPrimaryKey(Integer ur_id);

    List<User> selectAll();

    int updateUser(User user);
    
    List<User> get( User user );
    
    //根据用户id 更新环保值
    int updateScores(Integer ur_id);
}