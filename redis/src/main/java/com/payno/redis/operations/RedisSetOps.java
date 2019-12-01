package com.payno.redis.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author payno
 * @date 2019/12/1 20:44
 * @description
 */
@Component
public class RedisSetOps {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    SetOperations<String,String> setOps;
    @PostConstruct
    private void initOps(){
        setOps=stringRedisTemplate.opsForSet();
    }

    /**
     * 增加/删除元素
     * sadd key values[ value1 value2...] : 向set中添加数据，如果key的值已经存在则不会重复添加。
     * srem key members[member1 member2...] ： 删除set中指定的成员
     */
    public void addAndRem(){
        setOps.add("set1","1","2","3","a");
        setOps.add("set2","a","b","c","1");
        setOps.remove("set1","2");
        setOps.remove("set2","b");
    }

    /**
     * 获得集合的元素
     * smembers key : 获取set中所有的成员
     * sismember key member：判断参数指定的成员是否在该set中，1表示存在，0表示不存在，或者该key本身不存在。
     */
    public void collection(){
        setOps.members("set1").forEach(System.out::println);
        System.out.println(setOps.isMember("set1","b"));
    }

    /**
     * collections ops:
     *     sdiff,sinter,sunion
     */
    public void sets(){
        setOps.difference("set1","set2").forEach(System.out::print);
        System.out.println();
        setOps.intersect("set1","set2").forEach(System.out::print);
        System.out.println();
        setOps.union("set1","set2").forEach(System.out::print);
    }
}
