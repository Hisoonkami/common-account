package com.adev.common.account.repository;

import com.adev.common.account.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * 根据代码查找角色
     * @param roleCode
     * @return
     */
    Role findFirstByRoleCode(String roleCode);
}
