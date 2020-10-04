package com.adev.common.account.service.impl;

import com.adev.common.account.domain.User;
import com.adev.common.base.service.impl.BaseServiceImpl;
import com.adev.common.base.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adev.common.account.repository.UserRepository;
import com.adev.common.account.service.UserService;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	@Transactional
	public User addUser(String loginName, String password, String userName) {
		if(!existLoginName(null,loginName)){
			User user=new User();
			user.setLoginName(loginName);
			user.setPassword(MD5Utils.MD5Encode(password,null));
			user.setUsername(userName);
			userRepository.save(user);
			return user;
		}
		return null;
	}

	private boolean existLoginName(Long userId,String loginName){
		User user=userRepository.findFristByLoginName(loginName);
		if(null!=user&&(null==userId||!userId.equals(user.getUserId()))){
			return true;
		}
		return false;
	}
}
