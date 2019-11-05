package com.common.controller;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
import com.common.bean.ResultData;
 
public class BaseController  {
	 
	protected   final Log loger = LogFactory.getLog( getClass());
	protected    void debug(String msg) {
		loger.debug(msg);	
	}
	 
	protected   void info(String msg) {
		loger.info(msg);	
	}
	
	protected   void error(String msg) {
		loger.error(msg);	
	}
	
	public static ResultData quickReturn(boolean ok) {
		if(ok ) {
			return ResultData.success();
		}else {
			return ResultData.fail();
		}
	}
	public static ResultData quickReturn (int row) {
		 return quickReturn(row > 0);
	}
	
	public static ResultData quickReturn (List list) {
		if( list != null && list.size() >0 ) {
			return ResultData.success().setData(list);
		}else {
			return ResultData.fail().setData(list);
		}
	}
}
