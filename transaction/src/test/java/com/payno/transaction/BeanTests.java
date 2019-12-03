package com.payno.transaction;

import com.payno.transaction.Test.Bean;
import com.payno.transaction.Test.Config;
import com.payno.transaction.Test.ContextHolder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransactionApplication.class)
class BeanTests {
    @Autowired
    Config config;
    @Autowired
    ContextHolder contextHolder;
    @Test
    void contextLoads() {
        Config config=contextHolder.get().getBean(Config.class);
        config.setBean(new Bean("new"));
        config.print();
    }
}
