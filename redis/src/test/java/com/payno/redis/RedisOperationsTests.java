package com.payno.redis;

import com.payno.redis.operations.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author payno
 * @date 2019/12/1 16:58
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisOperationsTests {
    @Autowired
    RedisStringOps redisStringOps;
    @Autowired
    RedisHashOps redisHashOps;
    @Autowired
    RedisListOps redisListOps;
    @Autowired
    RedisSetOps redisSetOps;
    @Autowired
    RedisSortedSetOps redisSortedSetOps;


    @Test
    public void stringOps(){
        //redisStringOps.operations();
        //redisStringOps.multi();
        redisStringOps.toJavaObj();
    }

    @Test
    public void hashOps(){
        redisHashOps.set();
        redisHashOps.get();
        redisHashOps.del();
    }

    @Test
    public void listOps(){
        redisListOps.push();
        redisListOps.query();
        redisListOps.pop();
    }

    @Test
    public void setOps(){
        redisSetOps.addAndRem();
        redisSetOps.collection();
        redisSetOps.sets();
    }

    @Test
    public void zSetOps(){
        redisSortedSetOps.addAndRem();
        redisSortedSetOps.query();
    }
}
