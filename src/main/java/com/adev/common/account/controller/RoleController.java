package com.adev.common.account.controller;

import com.adev.common.account.domain.Role;
import com.adev.common.account.service.RoleService;
import com.adev.common.base.domian.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping(value = {"/api/roles"})
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<BaseResult> addRole(@RequestParam(value = "roleName") String roleName,
											  @RequestParam(value = "roleCode") String roleCode,
											  @RequestParam(value = "remark",required = false) String remark){
		return ResponseEntity.ok(BaseResult.success(roleService.addRole(roleName,roleCode,remark)));
	}

	@RequestMapping(value = {"/{id}"},method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<BaseResult> updateRole(@PathVariable("id") Long id,
												 @RequestParam(value = "roleName",required = false) String roleName,
												 @RequestParam(value = "roleCode",required = false) String roleCode,
												 @RequestParam(value = "remark",required = false) String remark){
		return ResponseEntity.ok(BaseResult.success(roleService.updateRole(id,roleName,roleCode,remark)));
	}

	@RequestMapping(value = {"/{id}"},method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<BaseResult> deleteRole(@PathVariable("id") Long id){
		return ResponseEntity.ok(BaseResult.success(roleService.deleteRole(id)));
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<BaseResult> findAllRole(){
		Iterable<Role> roleIterable=roleService.findAll();
		return ResponseEntity.ok(BaseResult.success(roleIterable.iterator()));
	}

	@RequestMapping(value = {"/{id}/permissions"},method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<BaseResult> addPermissions(@PathVariable("id") Long id,@RequestParam(value = "permissionIds")Long[] permissionIds){
		return ResponseEntity.ok(BaseResult.success(roleService.addPermissions(id,permissionIds)));
	}

	@RequestMapping(value = {"/search/findByUser"},method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<BaseResult> findByUser(@RequestParam("userId") Long userId){
		return ResponseEntity.ok(BaseResult.success(roleService.findByUserId(userId)));
	}
}
