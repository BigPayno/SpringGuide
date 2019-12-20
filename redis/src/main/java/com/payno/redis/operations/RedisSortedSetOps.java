package com.payno.redis.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author payno
 * @date 2019/12/1 20:52
 * @description
 */
@Profile("jedis")
@Component
public class RedisSortedSetOps {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    ZSetOperations<String,String> zSetOps;
    @PostConstruct
    private void initOps(){
        zSetOps=stringRedisTemplate.opsForZSet();
    }

    /**
     * 添加元素
     * zadd key score member score2 member2...:将成员以及该成员的分数存放到sortedset中。
     * 如果该元素已经存在则会用新的分数替换原有的分数。返回值是新加入到集合的元素的个数，不包含之前已经存在的元素。
     * 删除元素
     * zrem key member [member...] : 移除集合中指定的成员，可以指定多个成员
     * 返回增加或者移除的元素个数
     */
    public void addAndRem(){
        zSetOps.add("tasks","task1",1);
        zSetOps.add("tasks","task2",2);
        zSetOps.add("tasks","task3",3);
        zSetOps.add("tasks","task4",4);
        zSetOps.add("tasks","task5",5);
        zSetOps.remove("tasks","task1");
        zSetOps.removeRangeByScore("tasks",2,3);
        //zSetOps.removeRange("tasks",0,-1);
    }

    /**
     * 获取元素
     * zscore key member :返回指定成员的分数
     * zcard key : 获取集合中成员数量
     * 范围查询
     * zrange key start end [withscores] : 获取集合中脚注为start-end的成员，[withscores]参数表明返回的成员包含其分数。
     * zrevrange key start stop [withscores] : 按照分数从大到小的顺序返回索引从start到stop之间的所有元素（包含两端的元素）。
     * zremrangebyrank key start stop : 按照排名范围删除元素
     */
    public void query(){
        System.out.println(zSetOps.score("tasks","task4"));
        System.out.println(zSetOps.zCard("tasks"));
        System.out.println(zSetOps.rangeByScore("tasks",2,5));
    }
}
