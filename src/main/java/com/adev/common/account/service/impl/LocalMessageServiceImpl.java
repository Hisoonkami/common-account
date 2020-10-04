package com.adev.common.account.service.impl;

import com.adev.common.account.domain.LocalMessage;
import com.adev.common.account.repository.LocalMessageRepository;
import com.adev.common.account.service.LocalMessageService;
import com.adev.common.base.enums.Enums;
import com.adev.common.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalMessageServiceImpl extends BaseServiceImpl<LocalMessage, String> implements LocalMessageService {
    private final LocalMessageRepository localMessageRepository;

    @Autowired
    public LocalMessageServiceImpl(LocalMessageRepository localMessageRepository) {
        super(localMessageRepository);
        this.localMessageRepository = localMessageRepository;
    }

    @Override
    public List<LocalMessage> findByState(Enums.LocalMessageState state) {
        return localMessageRepository.findByState(state);
    }
}
