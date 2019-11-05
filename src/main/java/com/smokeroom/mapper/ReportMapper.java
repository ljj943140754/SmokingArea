package com.smokeroom.mapper;

import com.smokeroom.entity.Report;
import java.util.List;

public interface ReportMapper {
    int deleteByPrimaryKey(Integer rp_id);

    int insert(Report record);

    Report selectByPrimaryKey(Integer rp_id);

    List<Report> selectAll();

    int updateByPrimaryKey(Report record);
}