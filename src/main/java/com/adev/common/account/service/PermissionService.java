package com.adev.common.account.service;

import com.adev.common.account.domain.Permission;
import com.adev.common.base.service.BaseService;

import java.util.List;

public interface PermissionService extends BaseService<Permission, Long> {
    /**
     * 添加权限
     * @param permissionName
     * @param permissionCode
     * @param remark
     * @return
     */
    Long addPermission(String permissionName,String permissionCode,String remark);

    /**
     * 删除权限
     * @param id
     * @return
     */
    boolean deletePermission(Long id);

    /**
     * 修改权限
     * @param id
     * @param permissionName
     * @param permissionCode
     * @param remark
     * @return
     */
    boolean updatePermission(Long id,String permissionName,String permissionCode,String remark);

    /**
     * 获取指定角色的权限列表
     * @param roleId
     * @return
     */
    List<Permission> findByRole(Long roleId);

    /**
     * 鉴权
     * @param userId
     * @param permissionCode
     * @return
     */
    boolean authentication(Long userId,String permissionCode);
}
