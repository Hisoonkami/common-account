package com.adev.common.account.service.impl;

import com.adev.common.account.domain.User;
import com.adev.common.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adev.common.account.repository.UserRespository;
import com.adev.common.account.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

	private final UserRespository userRespository;
	
	@Autowired
	public UserServiceImpl(UserRespository userRespository) {
		super(userRespository);
		this.userRespository=userRespository;
	}
	
	@Override
	public User findByLoginName(String loginName) {
		return userRespository.findFristByLoginName(loginName);
	}

}
