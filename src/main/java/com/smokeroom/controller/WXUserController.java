package com.smokeroom.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.common.bean.CommonUser;
import com.common.bean.ResultData;
import com.common.controller.BaseController;
import com.common.enu.HttpSessionKey;
import com.common.enu.Role;
import com.common.utils.HttpUtils;
 
 
@RestController
@RequestMapping("wxuc")
public class WXUserController extends BaseController {
	@Value("${wx.appid}")
	private String appid;
	@Value("${wx.secret}")
	private String secret;
	
	  
	@PostMapping("logout.action")
	public ResultData logout(HttpServletRequest rq ) {
		HttpSession ss = rq.getSession(false);
		if(ss != null) {
			ss.invalidate();
			info("正在为您注销！");
		}else {
			info("您未登陆，无需注销");
		}
		return ResultData.success();
	}
	
	/**
	  * 只会是用户。不会是巡更人员。 后台登陆。
	 * @param code
	 * @return
	 */
	@GetMapping("getuserinfo.action")
	public ResultData getUserInfo1( String code ,HttpServletRequest rq ) {
		String js_code = code;
		String grant_type = "authorization_code";
		String url = getRequestURL(appid, secret, js_code, grant_type);
		String response = sendRequestAndGetResponse(url);
		Result1 result1 = JSONObject.parseObject(response, Result1.class);
		int rs_code  = result1.getErrcode();
		String rs_openid = result1.getOpenid();
		if( rs_code ==0 ) {
			HttpSession ss = rq.getSession();
			//逻辑
			//1 先查询用户，没有则添加。
			//2 有则更新。头像，昵称，微信号，性别，
			//3 将用户信息。放入Session中。
			CommonUser cmu = new CommonUser();
			//cmu.setRole(Role.USER);
			rq.getSession().setAttribute(HttpSessionKey.USER_SESSION_KEY.getCode(), cmu);
			return ResultData.success("已经登陆").setData(rs_openid);
			
		}else {
			return ResultData.fail(result1.getErrmsg());
		}
	}
	


	/**
	 * 获取unionid
	 * @return
	 */
	/*
	@RequestMapping("getinfo2.action")
	@ResponseBody
	public Map<String,String> getUserInfo2(String rawData,String signature,String encryptedData, String iv ) {
		System.out.println("获取解密信息。。。。session_key=："+result1.getSession_key());
		String rs = AECUtils.decrypt(iv, result1.getSession_key(), encryptedData);
		System.out.println("解密信息为：");
		System.out.println(rs);
		Map<String,String> map = new HashMap<String,String>();
		map.put("secret", rs);
		return map ;
	}
	 */
	public static String getRequestURL(String appid,	String secret ,String js_code,String grant_type) {
		StringBuilder url = new StringBuilder();
		url.append("https://api.weixin.qq.com/sns/jscode2session?") 
		   .append("appid=").append(appid).append("&")
		   .append("secret=").append(secret).append("&")
		   .append("js_code=").append(js_code).append("&")
		   .append("grant_type=").append(grant_type);
		return url.toString();
	}
	
	private static String sendRequestAndGetResponse(String url_str) {
		try {
			return HttpUtils.get(url_str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static class Result1{
		private String openid;
		private String session_key;
		private String unionid;
		private int errcode;
		private String errmsg;
		public String getOpenid() {
			return openid;
		}
		public void setOpenid(String openid) {
			this.openid = openid;
		}
		public String getSession_key() {
			return session_key;
		}
		public void setSession_key(String session_key) {
			this.session_key = session_key;
		}
		public String getUnionid() {
			return unionid;
		}
		public void setUnionid(String unionid) {
			this.unionid = unionid;
		}
		public int getErrcode() {
			return errcode;
		}
		public void setErrcode(int errcode) {
			this.errcode = errcode;
		}
		public String getErrmsg() {
			return errmsg;
		}
		public void setErrmsg(String errmsg) {
			this.errmsg = errmsg;
		}
		@Override
		public String toString() {
			return "Result1 [openid=" + openid + ", session_key=" + session_key + ", unionid=" + unionid + ", errcode="
					+ errcode + ", errmsg=" + errmsg + "]";
		}
	 
	}
	
}
