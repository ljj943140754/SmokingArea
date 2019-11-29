package com.smokeroom.service;

import com.common.bean.ResultData;
import com.smokeroom.entity.Activity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author haiger412
 * @since 2019-10-30
 */
public interface IActivityService{
	ResultData ActivityHandle(Activity act);
}
