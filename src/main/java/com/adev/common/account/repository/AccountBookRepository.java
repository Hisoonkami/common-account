package com.adev.common.account.repository;

import com.adev.common.account.domain.AccountBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBookRepository extends JpaRepository<AccountBook, Long> {

}
