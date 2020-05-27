package com.payno.redis.clients.config;

import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisCacheManager;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author payno
 * @date 2019/12/1 16:12
 * @description
 * @cluster:集群模式<高并发>
 *
 * @sentinel:哨兵模式（主从模式）<高可用>
 *  1.哨兵和复制：保持高可用的关键
 *  2.主观下线与客观下线：一个哨兵发现主结点down掉是主观，半数就是客观
 * @特点
 *  1.主从模式：读写分离，备份，一个Master可以有多个Slaves。
 *  2.哨兵sentinel：监控，自动转移，哨兵发现主服务器挂了后，就会从slave中重新选举一个主服务器。
 *  3.集群：为了解决单机Redis容量有限的问题，将数据按一定的规则分配到多台机器，内存/QPS不受限于单机，可受益于分布式集群高扩展性。
 *
 */
public class ClusterAndSentinel {
    /**
     *      哨兵模式(高可用：监控，自动转移)
     *      sentinel:
     *          master:
     *          nodes:
     *
     *      集群模式(高并发：为了解决单机Redis容量有限的问题)
     *      cluster:
     *          nodes:
     *
     */
    public void patterns() {
        RedisProperties redisProperties = new RedisProperties();
        //  具体配置模式结合autoConfig 可以了解spring配置方式
        if(redisProperties.getCluster()==null&&redisProperties.getSentinel()==null){
            System.out.println("单机Redis");
        }else if(redisProperties.getCluster()!=null){
            System.out.println("集群Redis");
        }else{
            System.out.println("哨兵Redis");
        }
    }

    /**
     *  shiro<>--->CacheManager(shiro)
     *                  <|-----RedisCacheManager(crazyCake)
     *                              <>----->IRedisManager(crazyCake)
     *
     *  IRedisManager <|-----WorkAloneRedisManager<|-----RedisSentinelManager（哨兵）/RedisManager(单机)
     *                <|-----RedisClusterManager
     *
     *  需要注意的是crazyCake的IRedisManager的实现大多依赖Jedis
     *  如果Spring配置了其他的连接Redis的工厂，服务就会创建多个不同的jedis工厂，甚至可能因为没有jedis对象而出问题
     *  为了解决这样的问题，需要深入RestTemplate的创建方式，并以RestTemplate的创建方式来实现IRedisManager
     *  这样就可以实现Spring和crazyCake的兼容
     */
    public void cacheManager(IRedisManager redisManager){
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisManager(redisManager);
    }
}
