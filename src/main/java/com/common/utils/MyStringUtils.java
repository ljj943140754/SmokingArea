package com.common.utils;

public class MyStringUtils {
	public static boolean isEmpty(String field) {
		if(field == null || field.equals("") || field.trim().equals("") )return true;
		return false;
	}
}
