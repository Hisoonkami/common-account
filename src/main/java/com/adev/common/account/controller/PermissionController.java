package com.adev.common.account.controller;

import com.adev.common.account.domain.User;
import com.adev.common.account.service.PermissionService;
import com.adev.common.account.service.UserService;
import com.adev.common.base.domian.BaseResult;
import com.adev.common.base.enums.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping(value = {"/api/permissions"})
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<BaseResult> addPermission(@RequestParam("permissionName") String permissionName,
													@RequestParam("permissionCode") String permissionCode,
													@RequestParam(value = "remark",required = false) String remark){
		return ResponseEntity.ok(BaseResult.success(permissionService.addPermission(permissionName,permissionCode,remark)));
	}

	@RequestMapping(value = {"/{id}"},method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<BaseResult> updatePermission(@PathVariable("id") Long id,
													   @RequestParam(value = "permissionName",required = false) String permissionName,
													   @RequestParam(value = "permissionCode",required = false) String permissionCode,
													   @RequestParam(value = "remark",required = false) String remark){
		return ResponseEntity.ok(BaseResult.success(permissionService.updatePermission(id,permissionName,permissionCode,remark)));
	}

	@RequestMapping(value = {"/{id}"},method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<BaseResult> deletePermission(@PathVariable("id") Long id){
		return ResponseEntity.ok(BaseResult.success(permissionService.deletePermission(id)));
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<BaseResult> findAllPermission(){
		return ResponseEntity.ok(BaseResult.success(permissionService.findAll()));
	}

	@RequestMapping(value = {"/search/findByRole"},method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<BaseResult> findByRole(@RequestParam("roleId") Long roleId){
		return ResponseEntity.ok(BaseResult.success(permissionService.findByRole(roleId)));
	}

	@RequestMapping(value = {"/authentication"},method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<BaseResult> authentication(@RequestParam("loginName") String loginName,
													 @RequestParam("permissionCode")String permissionCode){
		User user=userService.findByLoginName(loginName);
		if(null!=user){
			boolean auth=permissionService.authentication(user.getUserId(),permissionCode);
			if(auth){
				return ResponseEntity.ok(BaseResult.success(auth));
			}else {
				return ResponseEntity.ok(BaseResult.failure(ResultEnum.PERMISSION_NO_ACCESS));
			}
		}
		return ResponseEntity.ok(BaseResult.failure(ResultEnum.PERMISSION_NO_ACCESS));
	}
}
