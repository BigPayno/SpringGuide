package com.payno.rocketmq.faststart;

import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/11/24 15:43
 * @description
 */
@Component
public class MqService {
    @Autowired
    MQProducer producer;
    @Autowired
    MQConsumer consumer;
    public void productMessage(Message message) throws Exception{
        SendResult result=producer.send(message);
    }
    public void consumeMessage() throws Exception{

    }
}
