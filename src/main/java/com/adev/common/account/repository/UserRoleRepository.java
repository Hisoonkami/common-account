package com.adev.common.account.repository;

import com.adev.common.account.domain.Role;
import com.adev.common.account.domain.UserRole;
import com.adev.common.account.domain.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleKey> {

    @Query(value = "select role from Role as role,UserRole as userRole where role.id=userRole.roleId and userRole.userId=?1")
    List<Role> findRoleByUserId(Long userId);
}
