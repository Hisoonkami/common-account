package com.adev.common.account.service;

import com.adev.common.account.domain.AccountBook;
import com.adev.common.base.service.BaseService;

import java.math.BigDecimal;


public interface AccountBookService extends BaseService<AccountBook, Long> {
    /**
     * 支付并下单
     * @param loginName 用户登录名
     * @param exchange 交易所
     * @param currencyPair 交易对
     * @param price 单价
     * @param number 成交数量
     * @return
     */
    Long orderPayment(String loginName, String exchange, String currencyPair, BigDecimal price,BigDecimal number);
}
