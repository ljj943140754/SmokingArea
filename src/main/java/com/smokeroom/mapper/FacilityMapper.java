package com.smokeroom.mapper;

import com.smokeroom.entity.Facility;
import java.util.List;

public interface FacilityMapper {
    int deleteByPrimaryKey(Integer fy_id);

    int insert(Facility record);

    List<Facility> getFacility(Facility record);

    List<Facility> selectAll();

    int updateByPrimaryKey(Facility record);
}