package com.adev.common.account.service;

import com.adev.common.account.domain.Role;
import com.adev.common.account.domain.User;
import com.adev.common.base.service.BaseService;

import java.util.List;

public interface RoleService extends BaseService<Role, Long> {
	/**
	 * 添加角色
	 * @param roleName
	 * @param roleCode
	 * @param remark
	 * @return
	 */
	Long addRole(String roleName,String roleCode,String remark);

	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	boolean deleteRole(Long id);

	/**
	 * 修改角色
	 * @param id
	 * @param roleName
	 * @param roleCode
	 * @param remark
	 * @return
	 */
	boolean updateRole(Long id,String roleName,String roleCode,String remark);

	/**
	 * 添加权限
	 * @param id
	 * @param permissionIds
	 * @return
	 */
	boolean addPermissions(Long id,Long[] permissionIds);

	/**
	 * 授权
	 * @param userId
	 * @param roleId
	 * @return
	 */
	boolean grantAuthorization(Long userId,Long roleId);

	/**
	 * 获取用户的角色
	 * @param userId
	 * @return
	 */
	List<Role> findByUserId(Long userId);
}
