package com.smokeroom.service;

import com.common.bean.ResultData;
import com.smokeroom.entity.Facility;

/**
 * <p>
 *  用户痕迹 服务类
 * </p>
 *
 * @author haiger412
 * @since 2019-10-30
 */
public interface ITraceService{
	ResultData updateUserTrace(Facility fac);
}
