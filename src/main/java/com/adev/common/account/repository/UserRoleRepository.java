package com.adev.common.account.repository;

import com.adev.common.account.domain.UserRole;
import com.adev.common.account.domain.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleKey> {

}
