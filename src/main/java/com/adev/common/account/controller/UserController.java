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
	
	@RequestMapping(value = {"/login"},method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<BaseResult> login(String loginName, String password){
		if(StringUtils.isBlank(loginName)||StringUtils.isBlank(password)) {
			return ResponseEntity.ok(BaseResult.failure(ResultEnum.PARAM_IS_BLANK));
		}
		User user=userService.findByLoginName(loginName);
		if(null==user) {
			return ResponseEntity.ok(BaseResult.failure(ResultEnum.USER_NOT_EXIST));
		}
		
		if(!MD5Utils.MD5Encode(password, null).equals(user.getPassword())) {
			return ResponseEntity.ok(BaseResult.failure(ResultEnum.USER_LOGIN_ERROR));
		}
		return ResponseEntity.ok(BaseResult.success(user));
	}
}
