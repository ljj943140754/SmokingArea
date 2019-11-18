package com.smokeroom.service;

import com.smokeroom.entity.ActivityJoinIn;
import com.baomidou.mybatisplus.extension.service.IService;
import com.common.bean.ResultData;

/**
 * <p>
 *  活动报名服务类
 * </p>
 *
 * @author haiger412
 * @since 2019-10-30
 */
public interface IActivityJoinInService extends IService<ActivityJoinIn> {
	 ResultData activityPocessing(ActivityJoinIn act);
}
