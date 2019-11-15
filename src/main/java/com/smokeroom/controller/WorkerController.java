package com.smokeroom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ano.Permission;
import com.common.bean.CommonUser;
import com.common.bean.ResultData;
import com.common.controller.BaseController;
import com.common.enu.HttpSessionKey;
import com.common.enu.Role;
import com.common.utils.MyStringUtils;
import com.common.utils.SendSms;
import com.smokeroom.entity.User;
import com.smokeroom.entity.Worker;
import com.smokeroom.entity.ext.WorkerLoginVO;
import com.smokeroom.mapper.UserMapper;
import com.smokeroom.mapper.WorkerMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author haiger412
 * @since 2019-10-30
 */
@Api("用户管理")
@RestController
@RequestMapping("/worker")
public class WorkerController extends BaseController {
	@Autowired
	private WorkerMapper mapper;
	
	@Autowired
	private UserMapper map;


	@ApiOperation("登陆检测")
	@GetMapping("checklogin.action")
	@Permission(role = { Role.ADMIN })
	public ResultData checklogin(HttpSession ss) {
		CommonUser cmu = (CommonUser) ss.getAttribute(HttpSessionKey.USER_SESSION_KEY.getCode());
		if (cmu != null) {
			return ResultData.success().setData(cmu);
		}
		return ResultData.fail();
	}

	@ApiOperation("添加工作人员管理")
	@PostMapping("insert.action")
	@Permission(role = { Role.ADMIN })
	public ResultData insert(Worker wor) {
		info("用户添加：" + wor);
		return quickReturn(mapper.insert(wor));
	}

	@ApiOperation("删除员工管理")
	@PostMapping("deleteWorker.action")
	@Permission(role = { Role.ADMIN })
	public ResultData delete(Worker wor) {
		info("删除员工" + wor);
		return quickReturn(mapper.deleteByPrimaryKey(wor.getWk_id()));
	}

	@ApiOperation("用户冻结 / 管理员修改工作人员信息")
	@PostMapping("Frozen.action")
	@Permission(role = { Role.ADMIN })
	public ResultData update(Worker wor) {
		info("用户冻结修改：" + wor);
		return quickReturn(mapper.updateByPrimaryKey(wor));
	}

	@ApiOperation("员工修改自己信息")
	@PostMapping("updateinfo.action")
	@Permission(role = { Role.WORKER })
	public ResultData worUpdate(Worker wor) {
		info("用户修改手机号...：" + wor);
		return quickReturn(mapper.updateByPrimaryKey(wor));
	}
	
	
	@Permission(role={Role.ADMIN})
	@ApiOperation("查询员工列表/条件查询")
	@PostMapping("getList.action")
	public ResultData getWorkerList(Worker wor){
		return ResultData.success().setData(mapper.get(wor));
	}

	@ApiOperation("获取验证码")
	@PostMapping("getPhoneCode.action")
	public ResultData getPhoneCode(String phone, HttpSession ss) {
		Worker query = new Worker();
		query.setWk_phone(phone);
		List<Worker> list = mapper.get(query);
		if (list.size() == 0) {
			return ResultData.fail("该员工手机号不存在！请联系管理员添加！");
		}

		WorkerLoginVO vo = (WorkerLoginVO) ss.getAttribute("logininfo");
		if (vo == null) {
			// 第一次访问
			vo = new WorkerLoginVO();
			String phoneCode = RandomStringUtils.random(6, false, true);
			System.err.println("验证码：" + phoneCode);
			SendSms.send(phone, phoneCode);
			vo.setCode(phoneCode);
			vo.setPhone(phone);
			ss.setAttribute("logininfo", vo);
			return ResultData.success("验证码发送成功！");
		} else {
			// 判断时间有没有过期。
			long t2 = System.currentTimeMillis();
			long offset = t2 - vo.getT1();// 时间差值。
			if (offset >= 5 * 60 * 1000) {
				// 可以发送。
				String phoneCode = RandomStringUtils.random(6, false, true);
				SendSms.send(phone, phoneCode);
				vo.setCode(phoneCode);
				vo.setPhone(phone);// 防止再次获取时，手机号已经改变。
				vo.setT1(t2);// 更新上次时间，重新计算。
				return ResultData.success("验证码发送成功！");
			} else {
				// 还未超时。不能发送。
				return ResultData.fail("请于" + (5 * 60 * 1000 - offset) / 1000 + "s之后再重新获取");
			}
		}
	}

	@ApiOperation("员工登陆，小程序端。")
	@PostMapping("loginbyphone.action")
	public ResultData wxLogin(String phone, String vcode, HttpSession ss) {
		CommonUser cmu = (CommonUser) ss.getAttribute(HttpSessionKey.USER_SESSION_KEY.getCode());
		System.err.println("cmu:"+cmu);
		
		if (cmu != null) {
			return ResultData.success("登陆成功！").setData(cmu);
		}
		WorkerLoginVO vo = (WorkerLoginVO) ss.getAttribute("logininfo");
		System.err.println("vo:"+vo);
		
		if (vo == null) {
			return ResultData.fail("请先获取验证码！");
		}
		if (MyStringUtils.isEmpty(phone) || MyStringUtils.isEmpty(vcode)) {
			return ResultData.fail("手机号/验证码不能为空");
		}
		if (!vo.getCode().equals(vcode)) {
			return ResultData.fail("验证码错误！");
		}
		if (!vo.getPhone().equals(phone)) {
			return ResultData.fail("您上次获取验证码的手机号与本次手机号不一致！");
		}
		long t1 = System.currentTimeMillis();
		long offset = t1 - vo.getT1();
		if (offset >= 5 * 60 * 1000) {
			return ResultData.fail("验证码已经过期，请重新获取！");
		}
		Worker wk = new Worker();
		wk.setWk_phone(phone);
		List<Worker> list = mapper.get(wk);
		if (list.size() == 0) {
			return ResultData.fail("该员工手机号不存在！请联系管理员添加！");
		}
		wk = list.get(0);
		cmu = new CommonUser();
		if (wk.getWk_type().equals("worker")) {
			System.out.println("worker进入...");
			cmu.setRoles(new Role[]{Role.WORKER});
			cmu.setWorker(wk);

			//查询工作人员是否也是用户
			User user = new User();
			user.setUr_phone(phone);
			List<User> list2 = map.get(user);
			//用户表有纪录，则当前是工作人员 / 用户
			if(list2.size() > 0){
				User us = list2.get(0);
				cmu.setRoles(new Role[]{Role.WORKER,Role.USER});
				cmu.setUser(us);
			}

			ss.setAttribute(HttpSessionKey.USER_SESSION_KEY.getCode(), cmu);
		}else{
			return ResultData.fail("您不是工作人员不能登陆！"); 
		}
		wk.setWk_password(null);// 清空密码字段。
		ss.removeAttribute("logininfo");// 清空Session中的登陆验证码信息。
		return ResultData.success("登陆成功！").setData(cmu);
	}

	@ApiOperation("管理员登陆")
	@PostMapping("loginPC.action")
	public ResultData loginPC(Worker wk, HttpSession ss) {
		CommonUser cmu = (CommonUser) ss.getAttribute(HttpSessionKey.USER_SESSION_KEY.getCode());
		System.err.println("wk:" + wk);
		System.err.println("cmu:" + cmu);
		if (cmu != null) {
			return ResultData.success("登陆成功！").setData(cmu);
		}

		Worker query = new Worker();
		query.setWk_num(wk.getWk_num());
		query.setWk_password(wk.getWk_password());
		System.out.println("query:"+query);
		if (query.getWk_num() == null && query.getWk_password() == null) {
			return ResultData.fail("账号 / 密码不能为空!");
		}
		List<Worker> listworkers = mapper.get(query);
		System.out.println("listworkers:"+listworkers);
		if (listworkers.size() > 0) {
			cmu = new CommonUser();
			cmu.setRoles(new Role[]{Role.ADMIN});
			listworkers.get(0).setWk_password(null);
			cmu.setWorker(listworkers.get(0));
			ss.setAttribute(HttpSessionKey.USER_SESSION_KEY.getCode(), cmu);
			return ResultData.success("登陆成功！").setData(cmu);
		} else {

		}

		return ResultData.fail("登陆失败!");
	}
}
