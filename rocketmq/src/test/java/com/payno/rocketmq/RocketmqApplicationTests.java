package com.payno.rocketmq;

import com.payno.rocketmq.faststart.MqService;
import org.apache.rocketmq.common.message.Message;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RocketmqApplication.class)
class RocketmqApplicationTests {

    @Autowired
    MqService mqService;

    @Test
    void produce() throws Exception{
        Message message=new Message(
                "test",                   // topic
                "TagA",                         // tag
                "OrderID001",                  // key
                ("Hello MetaQ1").getBytes());  // body
        mqService.productMessage(message);
    }

    @Test
    void consume() throws Exception{
        Thread.sleep(3000);
    }

}
