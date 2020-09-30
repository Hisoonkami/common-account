package com.adev.common.account.controller;

import com.adev.common.account.domain.User;
import com.adev.common.account.service.UserService;
import com.adev.common.base.domian.BaseResult;
import com.adev.common.base.enums.ResultEnum;
import com.adev.common.base.utils.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = {"/api/users"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"/findByLoginName"},method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<BaseResult> findByLoginName(String loginName){
		User user=userService.findByLoginName(loginName);
		return ResponseEntity.ok(BaseResult.success(user));
	}
}
