package com.smokeroom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.bean.ResultData;
import com.smokeroom.entity.Facility;
import com.smokeroom.entity.Trace;
import com.smokeroom.entity.ext.userTraceVO;
import com.smokeroom.mapper.FacilityMapper;
import com.smokeroom.mapper.TraceMapper;
import com.smokeroom.service.ITraceService;

/**
 * <p>
 *  用户痕迹实现类
 * </p>
 *
 * @author haiger412
 * @since 2019-10-30
 */
@Service
public class ITraceServiceImpl implements ITraceService {

	@Autowired
	private FacilityMapper facilityMapper;
	@Autowired
	private TraceMapper traceMapper;
	
	@Override
	public ResultData updateUserTrace(userTraceVO utVO) {
		if(utVO.getTe_u_id()!=null){
			//1、封装设施对象查询出最近的设施
			Facility fac = new Facility();
			fac.setFy_lat(utVO.getFy_lat());
			fac.setFy_lng(utVO.getFy_lng());
			//2、根据经纬度查询设施返回设施列表
			List<Facility> list = facilityMapper.calcNearFacility(fac);
			//3、将对应的设施和用户id 插入痕迹表中
			Trace tra = new Trace();
			tra.setTe_u_id(utVO.getTe_u_id());
			tra.setTe_fy_id(list.get(0).getFy_id());
			return ResultData.success().setData(traceMapper.insert(tra));
		}
		return ResultData.fail("用户id不能为空！！");
	}

}
