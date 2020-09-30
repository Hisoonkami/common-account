package com.adev.common.account.service.impl;

import com.adev.common.account.domain.User;
import com.adev.common.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adev.common.account.repository.UserRepository;
import com.adev.common.account.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	}
	
	@Override
	public User findByLoginName(String loginName) {
		return userRepository.findFristByLoginName(loginName);
	}

}
