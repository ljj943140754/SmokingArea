package com.smokeroom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.common.ano.Permission;
import com.common.bean.ResultData;
import com.common.controller.BaseController;
import com.common.enu.Role;
import com.smokeroom.entity.Facility;
import com.smokeroom.mapper.FacilityMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author haiger412
 * @since 2019-10-30
 */
@Api("设施表管理")
@RestController
@RequestMapping("/facility")
public class FacilityController extends BaseController {
	@Autowired
	private FacilityMapper mapper;
	
	@ApiOperation("设施添加管理，管理员权限")
	@Permission(role = Role.ADMIN )
	@PostMapping("insert.action")
	public ResultData addFacility(Facility fac) {
		info("添加设施："+fac);
		return quickReturn(mapper.insert(fac));
	}
	
	@ApiOperation("设施删除管理，管理员权限")
	@Permission(role = Role.ADMIN )
	@PostMapping("delete.action")
	public ResultData delFacility(Facility fac) {
		info("删除设施："+fac);
		return quickReturn(mapper.deleteByPrimaryKey(fac.getFy_id()));
	}
	
	@ApiOperation("设施查找管理，管理员权限")
	@Permission(role = Role.ADMIN )
	@GetMapping("findFacility.action")
	public ResultData findFacility(Facility fac) {
		info("查找设施："+fac);
		return ResultData.success().setData((mapper.selectByPrimaryKey(fac)));
	}
	
	@ApiOperation("管理员删除 VR / 修改 VR")
	@Permission(role = Role.ADMIN )
	@PostMapping("deleteVR.action")
	public ResultData deleteVR(Facility fac) {
		info("查找设施："+fac);
		return ResultData.success().setData((mapper.updateByPrimaryKey(fac)));
	}
	
	

}
