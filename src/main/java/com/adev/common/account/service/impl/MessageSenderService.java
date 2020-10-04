package com.adev.common.account.service.impl;


import com.adev.common.account.domain.LocalMessage;
import com.adev.common.account.service.LocalMessageService;
import com.adev.common.base.enums.Enums;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Component
@Transactional
public class MessageSenderService implements ReturnCallback, ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private LocalMessageService localMessageService;

    public void send(String context,String localMessageId) {
        System.out.println("Sender发送内容 : " + context);
        this.rabbitTemplate.setMandatory(true);// 当Mandatory参数设为true时，如果目的不可达，会发送消息给生产者，生产者通过一个回调函数来获取该信息。
        this.rabbitTemplate.setConfirmCallback(this);//确认回调
        this.rabbitTemplate.setReturnCallback(this);//失败回退
        CorrelationData correlationData = new CorrelationData(localMessageId);//用于确认之后更改本地消息状态或删除--本地消息id
        this.rabbitTemplate.convertAndSend("exchange","topic.order", context,correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        String localMessageId = correlationData.getId();
        if (ack) {// 消息发送成功,更新本地消息为已成功发送状态或者直接删除该本地消息记录,剩余的由MQ投递到消费者端，消费者端需要进行幂等，避免产生脏数据
            LocalMessage message = new LocalMessage();
            message.setId(localMessageId);
            message.setState(Enums.LocalMessageState.SUCCESS);
            localMessageService.save(message);
//            localMessageService.deleteById(localMessageId); //或者直接删除掉
        } else {
            //失败处理 ，目前没有要处理的东西，初始化的就是失败
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("return--message:" + new String(message.getBody()) + ",replyCode:" + replyCode
                + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
    }
}
