package com.smokeroom.mapper;

import java.util.List;

import com.smokeroom.entity.SignInRecord;
import com.smokeroom.entity.ext.signInBO;

public interface SignInRecordMapper {
    int insert(SignInRecord record);

    List<SignInRecord> selectAll();
    
    int updateSignIn(signInBO BO);
    
}