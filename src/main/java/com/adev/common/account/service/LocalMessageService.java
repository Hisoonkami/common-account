package com.adev.common.account.service;

import com.adev.common.account.domain.AccountBook;
import com.adev.common.account.domain.LocalMessage;
import com.adev.common.base.enums.Enums;
import com.adev.common.base.service.BaseService;

import java.util.List;

public interface LocalMessageService extends BaseService<LocalMessage, String> {
    List<LocalMessage> findByState(Enums.LocalMessageState state);
}
