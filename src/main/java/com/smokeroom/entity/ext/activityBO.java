package com.smokeroom.entity.ext;

import java.util.List;

import com.smokeroom.entity.Activity;
import com.smokeroom.entity.ActivityUser;

public class activityBO extends Activity{
	private List<ActivityUser> ActivityEnrolment;

	public List<ActivityUser> getActivityEnrolment() {
		return ActivityEnrolment;
	}

	public void setActivityEnrolment(List<ActivityUser> activityEnrolment) {
		ActivityEnrolment = activityEnrolment;
	}

	@Override
	public String toString() {
		return "activityBO [ActivityEnrolment=" + ActivityEnrolment + "]";
	}


	
}
