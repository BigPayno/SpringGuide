package com.payno.redis.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author payno
 * @date 2019/12/1 20:23
 * @description
 *      在Redis中，List类型是按照插入顺序排序的字符串链表。和数据结构中的普通链表 一样，
 *  我们可以在其头部(left)和尾部(right)添加新的元素。在插入时，如果该键并不存在，R
 *  edis将为该键创建一个新的链表。与此相反，如果链表中所有的元素均被移除，那么该键
 *  也将会被从数据库中删除。
 */
@Profile("jedis")
@Component
public class RedisListOps {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    ListOperations<String,String> listOps;
    @PostConstruct
    public void initOps(){
        listOps=stringRedisTemplate.opsForList();
    }
    /**
     * 两端添加
     * lpush key value [value1 value2]... :
     *      在指定的key所关联的list的头部插入所有的values，
     *      如果该key不存在，该命令在插入前创建一个与该key关联的空链表，
     *      之后再向链表的头部插入数据。插入成功，返回元素的个数。
     *
     * rpush key value [value1 value2]... :
     *      在该list的尾部添加元素。
     */
    public void push(){
        listOps.leftPush("list","a");
        /**
         * 插入元素，a->b,a
         */
        listOps.set("list",0,"b");
        listOps.rightPush("list","d");
    }
    /**
     * 查看列表     lrange key start end
     *      获取链表中从start到end的元素的值，start、end从0开始计数；
     *      也可以为负数，若为-1则表示链表尾部的元素。
     */
    public void query(){
        System.out.println(listOps.index("list",1));
        System.out.println(listOps.range("list",0,-1));
    }

    /**
     *  两端弹出
     * lpop key ：
     *      返回并弹出指定key关联得链表中得第一个元素，即头部元素。如果该key不存在，返回nil。
     * rpop key :
     *      从尾部弹出元素。
     */
    public void pop(){
        System.out.println(listOps.leftPop("list"));
        System.out.println(listOps.rightPop("list"));
    }
}
