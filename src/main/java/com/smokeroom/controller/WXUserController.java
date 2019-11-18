package com.smokeroom.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.smokeroom.entity.User;
import com.smokeroom.entity.Worker;
import com.smokeroom.mapper.UserMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("微信端登陆")
@RestController
@RequestMapping("wxuc")
public class WXUserController extends BaseController {
	@Value("${wx.appid}")
	private String appid;
	@Value("${wx.secret}")
	private String secret;

	@Autowired
	private UserMapper mapper;

	/* CMS 小程序调用此接口完成注销 */
	@PostMapping("logout.action")
	public ResultData logout(HttpServletRequest rq) {
		HttpSession ss = rq.getSession(false);
		if (ss != null) {
			ss.invalidate();
			info("正在为您注销！");
		} else {
			info("您未登陆，无需注销");
		}
		return ResultData.success();
	}

	/**
	 * 只会是用户。不会是巡更人员。 后台登陆。
	 * 
	 * @param code
	 * @return
	 */
	@ApiOperation("用户微信登陆")
	@GetMapping("getuserinfo.action")
	public ResultData getUserInfo1(String code, User userInfo, HttpSession ss) {

		CommonUser cmu = (CommonUser) ss.getAttribute(HttpSessionKey.USER_SESSION_KEY.getCode());
		System.err.println("cmu的值" + cmu);
		String js_code = code;
		System.err.println("code+" + code);
		String grant_type = "authorization_code";
		String url = getRequestURL(appid, secret, js_code, grant_type);
		String response = sendRequestAndGetResponse(url);
		Result1 result1 = JSONObject.parseObject(response, Result1.class);
		int rs_code = result1.getErrcode();
		String rs_openid = result1.getOpenid();
		System.err.println("rs_openid+" + rs_openid);
		if (rs_code == 0) {
			// //1 先查询用户，没有则添加。
			User user = new User();
			user.setUr_openid(rs_openid);
			List<User> list = mapper.get(user);
			// CommonUser cmu = new CommonUser();
			// //2 有则更新。头像，昵称，微信号，性别，
			// //2.1如果过查询有则更新用户
			if (list.size() > 0) {
				User userDate = list.get(0);
				// 工作人员点击授权时user表有数据同时也更新手机号
				if (userInfo.getUr_phone() != null || userInfo.getUr_phone() != "") {
					userDate.setUr_phone(userInfo.getUr_openid());
				}
				userDate.setUr_avatarurl(userInfo.getUr_avatarurl());
				userDate.setUr_nickname(userInfo.getUr_nickname());
				mapper.updateUser(userDate);
				System.err.println("有该用户 ... " + userDate);
			} else {
				// //2.2如果数据库没有用户则添加
				User userInsert = new User();
				// 工作人员点击授权时user表没有数据则插入工作人员手机号
				if (userInfo.getUr_phone() != null || userInfo.getUr_phone() != "") {
					userInsert.setUr_phone(userInfo.getUr_openid());
				}
				userInsert.setUr_openid(rs_openid);
				userInsert.setUr_avatarurl(userInfo.getUr_avatarurl());
				userInsert.setUr_nickname(userInfo.getUr_nickname());
				mapper.insert(userInsert);
				System.err.println("无该用户 ... " + userInsert);

			}
			// //3 将用户信息。放入Session中。
			List<User> list2 = mapper.get(user);
			cmu.setUser(list2.get(0));
			cmu.setRoles(new Role[] { Role.USER });
			if(cmu!=null){
				if(cmu.getWorker()!=null){
					cmu.setWorker(cmu.getWorker());
					cmu.setRoles(new Role[] {Role.USER ,Role.WORKER});
				}
			}
				ss.setAttribute(HttpSessionKey.USER_SESSION_KEY.getCode(), cmu);
			
			return ResultData.success("已经登陆").setData(cmu);

		} else {
			return ResultData.fail(result1.getErrmsg());
		}

	}

	// 測試使用
	@GetMapping("Test.action")
	public ResultData Test(HttpSession ss) {
		CommonUser cmu = (CommonUser) ss.getAttribute(HttpSessionKey.USER_SESSION_KEY.getCode());
		System.err.println("cmu"+cmu);
		return ResultData.success();
	}

	/**
	 * 获取unionid
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping("getinfo2.action")
	 * 
	 * @ResponseBody public Map<String,String> getUserInfo2(String
	 * rawData,String signature,String encryptedData, String iv ) {
	 * System.out.println("获取解密信息。。。。session_key=："+result1.getSession_key());
	 * String rs = AECUtils.decrypt(iv, result1.getSession_key(),
	 * encryptedData); System.out.println("解密信息为："); System.out.println(rs);
	 * Map<String,String> map = new HashMap<String,String>(); map.put("secret",
	 * rs); return map ; }
	 */
	public static String getRequestURL(String appid, String secret, String js_code, String grant_type) {
		StringBuilder url = new StringBuilder();
		url.append("https://api.weixin.qq.com/sns/jscode2session?").append("appid=").append(appid).append("&")
				.append("secret=").append(secret).append("&").append("js_code=").append(js_code).append("&")
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

	public static class Result1 {
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
