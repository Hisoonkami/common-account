package com.adev.common.account.job;

import com.adev.common.account.domain.LocalMessage;
import com.adev.common.account.service.LocalMessageService;
import com.adev.common.account.service.impl.MessageSenderService;
import com.adev.common.account.utils.RedisUtil;
import com.adev.common.base.enums.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckLocalMessage {
    @Autowired
    private MessageSenderService senderService;
    @Autowired
    private LocalMessageService messageService;

    @Autowired
    private RedisUtil redisUtil;
    /**
     * 兜底方案:必须保证每个消息都发送到MQ消费端进行消费，保证数据最终一致
     *  每隔30秒检查本地消息表没有发送成功的消息，进行重试再次发送到MQ
     */
    @Scheduled(fixedDelay=1000*30L)
    public void checkMQLocalMessage(){
        List<LocalMessage> failStates=messageService.findByState(Enums.LocalMessageState.FAIL);
        if(failStates!=null&&failStates.size()>0){
            failStates.stream().forEach(messageFailstate->{
                redisUtil.lock(messageFailstate.getId(),messageFailstate.getId(),1000L);
                senderService.send(messageFailstate.getContext(), String.valueOf(messageFailstate.getId()));
                redisUtil.unlock(messageFailstate.getId(),messageFailstate.getId());
            });
        }
    }
}
