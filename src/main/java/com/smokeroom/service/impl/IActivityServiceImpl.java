package com.smokeroom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.bean.ResultData;
import com.smokeroom.entity.Activity;
import com.smokeroom.entity.ActivityTest;
import com.smokeroom.entity.ext.activityBO;
import com.smokeroom.entity.ext.activityVO;
import com.smokeroom.mapper.ActivityMapper;
import com.smokeroom.service.IActivityService;
@Service
public class IActivityServiceImpl implements IActivityService {

	@Autowired
	private ActivityMapper activitymapper;
	
	@Override
	public ResultData ActivityHandle(Activity act) {
		List<Activity> activityList = activitymapper.getActivity(act);
		List<activityBO> listBo= new ArrayList<activityBO>(); 
		
		for (Activity list : activityList) {
			activityVO acVo = (activityVO) list;
			activityBO acBo = new activityBO();
			acBo.setAt_id(list.getAt_id());
			acBo.setAt_uid(list.getAt_uid());
			acBo.setAt_title(list.getAt_title());
			acBo.setAt_status(list.getAt_status());
			acBo.setAt_max_count(list.getAt_max_count());
			acBo.setAt_creation(list.getAt_creation());
			acBo.setAt_content(list.getAt_content());
			acBo.setAt_sign_count(list.getAt_sign_count());
			acBo.setAt_start_date(list.getAt_start_date());
			acBo.setAt_end_date(list.getAt_end_date());
			acBo.setAt_issuer(list.getAt_issuer());
			acBo.setAt_address(list.getAt_address());
			
			if (acVo.getUserData() != null) {
				String data = acVo.getUserData();
				List<ActivityTest> ActivityTest = new ArrayList<ActivityTest>();
				String[] strings = data.split(",");
				for (String s : strings) {
					ActivityTest bo = new ActivityTest();
					String string1 = s.substring(0, s.indexOf("2"));
					String string2 = s.substring(s.indexOf("2"), s.indexOf("h")+1);
					String string3 = s.substring(s.indexOf("h"), s.length());
					System.out.println("string1:"+string1+"string2:"+string2+"string3"+string3);
					bo.setUr_nickname(s.substring(0, s.indexOf("2")));
					bo.setJi_creation(s.substring(s.indexOf("2"), s.indexOf("h")+1));
					bo.setUr_avatarurl(s.substring(s.indexOf("h"), s.length()));
					ActivityTest.add(bo);
					acBo.setActivityEnrolment(ActivityTest);
				}
			}else{
				acBo.setActivityEnrolment(null);
			}
			listBo.add(acBo);
		}
		return ResultData.success().setData(listBo);
	}

}
