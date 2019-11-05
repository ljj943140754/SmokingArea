package com.smokeroom.mapper;

import com.smokeroom.entity.Trace;
import java.util.List;

public interface TraceMapper {
    int deleteByPrimaryKey(Integer te_id);

    int insert(Trace record);

    Trace selectByPrimaryKey(Integer te_id);

    List<Trace> selectAll();

    int updateByPrimaryKey(Trace record);
}