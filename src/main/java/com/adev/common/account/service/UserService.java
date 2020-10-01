package com.adev.common.account.service;

import com.adev.common.account.domain.User;
import com.adev.common.base.service.BaseService;

public interface UserService extends BaseService<User, Long> {

	/**
	 * 根据登录名获取用户信息
	 * @param loginName
	 * @return
	 */
	public User findByLoginName(String loginName);

	/**
	 *
	 * @param loginName
	 * @param password
	 * @param userName
	 * @return
	 */
	User addUser(String loginName,String password,String userName);
}
