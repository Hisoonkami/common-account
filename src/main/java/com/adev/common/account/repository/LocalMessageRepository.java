package com.adev.common.account.repository;

import com.adev.common.account.domain.LocalMessage;
import com.adev.common.base.enums.Enums;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalMessageRepository extends JpaRepository<LocalMessage, String> {
    List<LocalMessage> findByState(Enums.LocalMessageState state);
}
