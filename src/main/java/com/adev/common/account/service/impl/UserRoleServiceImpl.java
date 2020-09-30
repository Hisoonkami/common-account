package com.adev.common.account.service.impl;

import com.adev.common.account.domain.UserRole;
import com.adev.common.account.domain.UserRoleKey;
import com.adev.common.account.repository.UserRoleRepository;
import com.adev.common.account.service.UserRoleService;
import com.adev.common.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, UserRoleKey> implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        super(userRoleRepository);
        this.userRoleRepository = userRoleRepository;
    }
}
