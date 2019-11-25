package com.payno.rocketmq.type;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.IntStream;

import static com.payno.rocketmq.type.Constant.*;
import static com.payno.rocketmq.type.Constant.TOPIC;

/**
 * @author payno
 * @date 2019/11/25 15:13
 * @description
 */
public class SyncPc {
    @Test
    public void produce() throws Exception{
        DefaultMQProducer producer=new DefaultMQProducer(CONCURRENT_GROUP);
        producer.setNamesrvAddr(URI);
        producer.start();

        /**
         * 同步打印10条消息
         */
        IntStream.range(0,100).forEach(key->{
            try{
                Message message=MsgFactory.index(key);
                SendResult result=producer.send(message,(list,selector,arg)->{
                    int index = (int) arg;
                    return list.get(index);
                },0);
                System.out.println(result);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        producer.shutdown();
    }

    /**
     * Junit????
     */
    public static void main(String[] args) throws Exception{
        SyncPc syncPc=new SyncPc();
        syncPc.consume();
    }

    @Test
    public void consume() throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(SYNC_GROUP);
        consumer.setNamesrvAddr(URI);
        consumer.setConsumeMessageBatchMaxSize(10);
        consumer.subscribe(TOPIC, "*");
        consumer.registerMessageListener((MessageListenerOrderly)(list, context)->{
            try{
                for (Message message:list){
                    String result=new String(message.getBody(),RemotingHelper.DEFAULT_CHARSET);
                    System.out.println(result);
                }
            }catch (Exception e){
                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
            }
            return ConsumeOrderlyStatus.SUCCESS;
        });
        consumer.start();
        Thread.sleep(20000);
    }
}
