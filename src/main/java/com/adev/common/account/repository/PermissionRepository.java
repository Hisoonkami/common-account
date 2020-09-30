package com.adev.common.account.repository;

import com.adev.common.account.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    /**
     * 根据名称查找
     * @param permissionName
     * @return
     */
    Permission findFirstByPermissionName(String permissionName);

    /**
     *
     * @param userId
     * @param permissionCode
     * @return
     */
    @Query(value = "select permission.* from user_role,role_permission,permission where user_role.user_id=?1 and user_role.role_id=role_permission.role_id and role_permission.permission_id=permission.id and permission.permission_code=?2",nativeQuery = true)
    List<Permission> findByUserIdAndPermissionCode(Long userId, String permissionCode);
}
