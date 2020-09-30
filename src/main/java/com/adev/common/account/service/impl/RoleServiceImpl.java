package com.adev.common.account.service.impl;

import com.adev.common.account.domain.Permission;
import com.adev.common.account.domain.Role;
import com.adev.common.account.repository.PermissionRepository;
import com.adev.common.account.repository.RoleRepository;
import com.adev.common.account.service.RoleService;
import com.adev.common.base.service.impl.BaseServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository=roleRepository;
    }

    @Override
    @Transactional
    public Long addRole(String roleName, String roleCode, String remark) {
        if(!existCode(null,roleCode)){
            Role role=new Role();
            role.setRoleCode(roleCode);
            role.setRoleName(roleName);
            role.setRemark(remark);
            roleRepository.save(role);
            return role.getId();
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteRole(Long id) {
        if(roleRepository.existsById(id)){
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateRole(Long id, String roleName, String roleCode, String remark) {
        Role role=roleRepository.findById(id).orElse(null);
        if(null!=role&&!existCode(id,roleCode)){
            if(StringUtils.isNotBlank(roleName)){
                role.setRoleName(roleName);
            }
            if(StringUtils.isNotBlank(roleCode)){
                role.setRoleCode(roleCode);
            }
            if(StringUtils.isNotBlank(remark)){
                role.setRemark(remark);
            }
            roleRepository.save(role);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean addPermissions(Long id, Long[] permissionIds) {
        Role role=roleRepository.findById(id).orElse(null);
        if(null!=role){
            if(null!=permissionIds&&permissionIds.length>0){
                List<Permission> permissionList=new ArrayList<>();
                for (Long permissionId:permissionIds){
                    Permission permission=permissionRepository.findById(permissionId).orElse(null);
                    if(null!=permission){
                        permissionList.add(permission);
                    }
                }
                role.setPermissionList(permissionList);
                roleRepository.save(role);
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        return null;
    }

    private boolean existCode(Long id,String roleCode){
        Role role=roleRepository.findFirstByRoleCode(roleCode);
        if(null!=role&&(null==id||!role.getId().equals(id))){
            return true;
        } else {
            return false;
        }
    }
}
