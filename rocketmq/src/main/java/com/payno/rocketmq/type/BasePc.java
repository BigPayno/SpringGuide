package com.payno.rocketmq.type;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.payno.rocketmq.type.Constant.*;

/**
 * @author payno
 * @date 2019/11/25 14:53
 * @description
 */
public class BasePc {
    @Test
    public void produce() throws Exception{
        /**
         * 创建生产者
         */
        DefaultMQProducer defaultMQProducer=new DefaultMQProducer(CONCURRENT_GROUP);
        defaultMQProducer.setNamesrvAddr(URI);
        defaultMQProducer.start();

        /**
         * 创建消息
         */
        Message message=new Message(
                TOPIC,
                "TAG_A",
                "KEYS_!",
                "HELLO！".getBytes(RemotingHelper.DEFAULT_CHARSET));

        /**
         * 发送消息
         */
        SendResult result=defaultMQProducer.send(message);
        System.out.println(message);
        defaultMQProducer.shutdown();
    }

    @Test
    public void consume() throws Exception{
        // 1、创建 DefaultMQPushConsumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(CONCURRENT_GROUP);

        // 2、设置 name server
        consumer.setNamesrvAddr(URI);

        // 设置消息拉取最大数
        consumer.setConsumeMessageBatchMaxSize(2);

        // 3、设置 subscribe
        consumer.subscribe(TOPIC, "*");

        // 4、创建消息监听
        consumer.registerMessageListener((MessageListenerConcurrently)(list,context)->{
                // 5、获取消息信息
                for (MessageExt msg : list) {
                    // 获取主题
                    String topic = msg.getTopic();
                    // 获取标签
                    String tags = msg.getTags();
                    // 获取信息
                    try {
                        String result = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                        System.out.println("Consumer 消费信息：topic：" + topic+ "，tags：" + tags + "，消息体：" + result);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                // 6、返回消息读取状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        // 7、启动消费者（启动后会阻塞）
        consumer.start();
        Thread.sleep(10000);
    }
}
