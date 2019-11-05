package com.common.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
 
import com.alibaba.fastjson.JSONObject;
import com.common.ano.Permission;
import com.common.bean.CommonUser;
import com.common.bean.ResultData;
import com.common.enu.HttpSessionKey;
import com.common.enu.Role;
 
 
 
/**
 * 检测登陆权限。
 * @author Administrator
 *
 */
public class CheckLogin implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest rq, HttpServletResponse rp, Object handler)
			throws Exception {
		 if(handler instanceof HandlerMethod) {
			 HandlerMethod hm = (HandlerMethod) handler;
			 Permission  perm =  hm.getMethod().getDeclaredAnnotation( Permission.class);
			 //1 方法没有添加该注解，则该方法不参与登陆检测逻辑。
			 if( perm == null )  
				 return true;
			 //2 如果有Permission注解，意味着全部要参与登陆检测逻辑。
			 HttpSession ss = rq.getSession(false);
			 if(ss == null) {
				  return  handleNotLoginResponse(rq,rp,"权限不足(errorcode=04001)，请先登录！");
			 }
			  CommonUser user =(CommonUser) ss.getAttribute( HttpSessionKey.USER_SESSION_KEY.getCode());
			  if(user == null ) {
				  return  handleNotLoginResponse(rq,rp,"权限不足(errorcode=04002)，请先登陆！");
			  }
			  Role [] roles = perm.role();
				//用户已经登陆，要进行权限匹配。
			  //一个用户拥有多个角色。.
				for(Role r  : roles ) {
					Role [] ros = user.getRoles();
					for(Role ro : ros) {
						if(  r ==  ro ) {
							return true;
						}
					}
				}
				 return  handleNotLoginResponse(rq,rp,"权限不足(errorcode=04003)！");
		 } 
		 //在此项目中，不会进入这里。
		 return true;
	}
	 
	/**
	 * 该方法永远返回false。
	 * 2个操作：1 拦截器不放行，controller无法执行。
	 *          2 将错误信息返回给前端。
	 * @param rp
	 * @param msg
	 * @return
	 */
	private boolean handleNotLoginResponse(HttpServletRequest rq,HttpServletResponse rp,String msg) {
		 ResultData rs  =  ResultData.fail(msg);
		 rp.setContentType("application/json;charset=UTF-8");
			try {
				rp.getOutputStream().write( JSONObject.toJSONString(rs).getBytes("UTF-8") );
			} catch (IOException e) {
				e.printStackTrace();
			}
		 return false;
	}
	 
	@Override
	public void postHandle(HttpServletRequest rq, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		 
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	 
	}

}
