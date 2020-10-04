package com.adev.common.account.service.impl;

import com.adev.common.account.domain.AccountBook;
import com.adev.common.account.domain.LocalMessage;
import com.adev.common.account.domain.User;
import com.adev.common.account.repository.AccountBookRepository;
import com.adev.common.account.service.AccountBookService;
import com.adev.common.account.service.LocalMessageService;
import com.adev.common.account.service.UserService;
import com.adev.common.base.enums.Enums;
import com.adev.common.base.service.impl.BaseServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.ExceptionEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AccountBookServiceImpl extends BaseServiceImpl<AccountBook, Long> implements AccountBookService {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountBookRepository accountBookRepository;

    @Autowired
    private LocalMessageService localMessageService;

    @Autowired
    private MessageSenderService messageSenderService;

    @Override
    @Transactional
    public Long orderPayment(String loginName, String exchange, String currencyPair, BigDecimal price, BigDecimal number) {
        BigDecimal amount=price.multiply(number);
        User user=userService.findByLoginName(loginName);
        if(null!=user&&amount.compareTo(user.getBalance())<=0){//用户存在并且余额充足
            user.setBalance(user.getBalance().subtract(amount));
            String orderId= UUID.randomUUID().toString();//提前生产订单id，防止出现重复操作（保证生成订单操作是幂等的）
            Map<String,Object> orderInfo=new HashMap<>();
            orderInfo.put("id",orderId);
            orderInfo.put("exchange",exchange);
            orderInfo.put("currencyPair",currencyPair);
            orderInfo.put("price",price);
            orderInfo.put("number",number);
            orderInfo.put("amount",amount);
            String context = JSONObject.toJSONString(orderInfo);
            LocalMessage localMessage=new LocalMessage();
            localMessage.setContext(context);
            localMessage.setId(UUID.randomUUID().toString());
            localMessage.setState(Enums.LocalMessageState.FAIL);
            localMessageService.save(localMessage);
            userService.save(user);//保存余额
            AccountBook accountBook=new AccountBook();
            accountBook.setOrderId(orderId);
            accountBook.setType(Enums.AccountBookType.PAY);
            accountBook.setAmount(amount);
            accountBookRepository.save(accountBook);
            messageSenderService.send(context,localMessage.getId());
        }
        return null;
    }
}
