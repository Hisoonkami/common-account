package com.adev.common.account.controller;

import com.adev.common.account.service.AccountBookService;
import com.adev.common.base.domian.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
@RequestMapping(value = {"/api/accountBooks"})
public class AccountBookController {
    @Autowired
    private AccountBookService accountBookService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BaseResult> orderPayment(@RequestParam("username") String username,
                                                   @RequestParam("exchange")String exchange,
                                                   @RequestParam("currencyPair")String currencyPair,
                                                   @RequestParam("price")BigDecimal price,
                                                   @RequestParam("number")BigDecimal number){
        Long accountBookId=accountBookService.orderPayment(username,exchange,currencyPair,price,number);
        return ResponseEntity.ok(BaseResult.success(accountBookId));
    }
}
