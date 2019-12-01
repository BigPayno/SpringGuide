package com.payno.redis.clients.config;

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
}
