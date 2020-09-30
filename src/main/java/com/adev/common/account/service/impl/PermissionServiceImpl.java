package com.adev.common.account.service.impl;

import com.adev.common.account.domain.Permission;
import com.adev.common.account.domain.Role;
import com.adev.common.account.repository.PermissionRepository;
import com.adev.common.account.service.PermissionService;
import com.adev.common.account.service.RoleService;
import com.adev.common.base.service.impl.BaseServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Long> implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        super(permissionRepository);
        this.permissionRepository=permissionRepository;
    }

    @Override
    @Transactional
    public Long addPermission(String permissionName, String permissionCode,String remark) {
        if(!existName(null,permissionName)){
            Permission permission=new Permission();
            permission.setPermissionName(permissionName);
            permission.setPermissionCode(permissionCode);
            permission.setRemark(remark);
            permissionRepository.save(permission);
            return permission.getId();
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deletePermission(Long id) {
        if(permissionRepository.existsById(id)){
            permissionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updatePermission(Long id, String permissionName,String permissionCode, String remark) {
        Permission permission=permissionRepository.findById(id).orElse(null);
        if(null!=permission&&!existName(id,permissionName)){
            if(StringUtils.isNotBlank(permissionName)){
                permission.setPermissionName(permissionName);
            }
            if(StringUtils.isNotBlank(permissionCode)){
                permission.setPermissionCode(permissionCode);
            }
            if(StringUtils.isNotBlank(remark)){
                permission.setRemark(remark);
            }
            permissionRepository.save(permission);
            return true;
        }
        return false;
    }

    @Override
    public List<Permission> findByRole(Long roleId) {
        Role role=roleService.findById(roleId).orElse(null);
        if(null!=role){
            return role.getPermissionList();
        }
        return null;
    }

    @Override
    public boolean authentication(Long userId, String permissionCode) {
        List<Permission> permissions=permissionRepository.findByUserIdAndPermissionCode(userId,permissionCode);
        if(null!=permissions&&!permissions.isEmpty()){
            return true;
        }
        return false;
    }

    private boolean existName(Long id,String permissionName){
        Permission permission=permissionRepository.findFirstByPermissionName(permissionName);
        if(null!=permission&&(null==id||!permission.getId().equals(id))){
            return true;
        } else {
            return false;
        }
    }
}
