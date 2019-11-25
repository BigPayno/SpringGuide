package com.payno.rocketmq.faststart;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * @author payno
 * @date 2019/11/24 15:31
 * @description
 *      一定要注意broker.q与broker.conf的配置
 *      特别是IP1大写，Id小写等，坑啊
 */
@Slf4j
@Configuration
public class MqConfig {
    private static final String MQ_SERVICE_URI="112.124.200.92:9876";
    private static final String MQ_PRODUCER_GROUP="Group_producer";
    private static final String MQ_CONSUMER_GROUP="Group_consumer";
    @Bean
    public MQProducer producer(){
        DefaultMQProducer producer=new DefaultMQProducer(MQ_PRODUCER_GROUP);
        producer.setNamesrvAddr(MQ_SERVICE_URI);
        try{
            producer.start();
        }catch (MQClientException e){
            log.error("Something wrong when creating producer[{}][{}]!",MQ_SERVICE_URI,MQ_PRODUCER_GROUP);
            log.error(""+e);
        }
        return producer;
    }

    @Bean
    public MQPushConsumer pushConsumer() throws MQClientException{
        DefaultMQPushConsumer pushConsumer=new DefaultMQPushConsumer(MQ_CONSUMER_GROUP);
        pushConsumer.setNamesrvAddr(MQ_SERVICE_URI);
        pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        pushConsumer.subscribe("test","*");
        pushConsumer.registerMessageListener((MessageListenerConcurrently)(msgs,context)->{
            try {
                Message msg = msgs.get(0);
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), new String(msgs.get(0).getBody()));
                String topic = msg.getTopic();
                String body = new String(msg.getBody(), StandardCharsets.UTF_8);
                String tags = msg.getTags();
                String keys = msg.getKeys();
                System.out.println("topic=" + topic + ",tags=" + tags + ",keys=" + keys + ",msg=" + body);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        pushConsumer.start();
        log.info("{} start!!!",pushConsumer);
        return pushConsumer;
    }
}
