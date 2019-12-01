package com.payno.redis.clients.jedis;

import redis.clients.jedis.Jedis;

/**
 * @author payno
 * @date 2019/12/1 15:49
 * @description
 */
public class JedisGuide {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.set("jedis","true");
        String value=jedis.get("jedis");
        System.out.println(value);
    }
}
