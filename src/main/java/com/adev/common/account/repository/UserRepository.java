package com.adev.common.account.repository;

import com.adev.common.account.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * 根据用户登录名获取用信息
	 * @param loginName
	 * @return
	 */
	User findFristByLoginName(String loginName);
}
