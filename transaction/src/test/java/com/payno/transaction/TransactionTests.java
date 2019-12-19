package com.payno.transaction;

import com.payno.transaction.demo.Service;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransactionApplication.class)
class TransactionTests {
    @Autowired
    Service service;
    @Test
    void contextLoads() throws Exception{
        String val="payno";
        try{
            service.run(val);
        }catch (Exception e){
            //确实回滚了
        }
        System.out.println(val);
    }
}
