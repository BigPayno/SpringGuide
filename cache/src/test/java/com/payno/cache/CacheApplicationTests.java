package com.payno.cache;

import com.payno.cache.base.CacheService;
import com.payno.cache.base.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class CacheApplicationTests {
    @Autowired
    CacheService service;
    @Autowired
    CacheManager manager;

    @Test
    void baseCache() {
        service.save(new User(Long.valueOf(11L),"payno"));
        service.save(new User(Long.valueOf(12L),"payno2"));
        //service.delete(new User(Long.valueOf(11L),null));
        User user=(User) manager.getCache("user").get(Long.valueOf(11L)).get();
        User user1=service.findById(Long.valueOf(11L));
        System.out.println(user.equals(user1));
    }

    @Test
    void multiCache(){
        service.saveM(new User(Long.valueOf(11L),"payno"));
        User user=(User) manager.getCache("user").get("payno").get();
        User user1=(User) manager.getCache("user").get(Long.valueOf(11L)).get();
        System.out.println(user.equals(user1));
    }
}
