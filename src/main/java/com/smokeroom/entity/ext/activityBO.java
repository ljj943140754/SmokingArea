package com.smokeroom.entity.ext;

import java.util.List;

import com.smokeroom.entity.Activity;
import com.smokeroom.entity.ActivityTest;

public class activityBO extends Activity{
	private List<ActivityTest> ActivityEnrolment;

	public List<ActivityTest> getActivityEnrolment() {
		return ActivityEnrolment;
	}

	public void setActivityEnrolment(List<ActivityTest> activityEnrolment) {
		ActivityEnrolment = activityEnrolment;
	}

	@Override
	public String toString() {
		return "activityBO [ActivityEnrolment=" + ActivityEnrolment + "]";
	}


	
}
