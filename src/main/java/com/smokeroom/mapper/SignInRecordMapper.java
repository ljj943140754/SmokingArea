package com.smokeroom.mapper;

import com.smokeroom.entity.SignInRecord;
import java.util.List;

public interface SignInRecordMapper {
    int insert(SignInRecord record);

    List<SignInRecord> selectAll();
}