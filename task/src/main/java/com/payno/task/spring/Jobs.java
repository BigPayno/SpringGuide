package com.payno.task.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author payno
 * @date 2020/3/24 09:44
 * @description
 */
@Profile("spring")
@Component
@Slf4j
public class Jobs {

    private final AtomicInteger counts = new AtomicInteger();

    /**
     *  @Scheduled 注解，设置定时任务的执行计划。
     *
     * 常用属性如下：
     *
     *     cron 属性：Spring Cron 表达式。例如说，"0 0 12 * * ?" 表示每天中午执行一次，"11 11 11 11 11 ?" 表示 11 月 11 号 11 点 11 分 11 秒执行一次（哈哈哈）。更多示例和讲解，可以看看 《Spring Cron 表达式》 文章。注意，以调用完成时刻为开始计时时间。
     *     fixedDelay 属性：固定执行间隔，单位：毫秒。注意，以调用完成时刻为开始计时时间。
     *     fixedRate 属性：固定执行间隔，单位：毫秒。注意，以调用开始时刻为开始计时时间。
     *     这三个属性，有点雷同，可以看看 《@Scheduled 定时任务的fixedRate、fixedDelay、cron 的区别》 ，一定要分清楚差异。
     *
     * 不常用属性如下：
     *
     *     initialDelay 属性：初始化的定时任务执行延迟，单位：毫秒。
     *     zone 属性：解析 Spring Cron 表达式的所属的时区。默认情况下，使用服务器的本地时区。
     *     initialDelayString 属性：initialDelay 的字符串形式。
     *     fixedDelayString 属性：fixedDelay 的字符串形式。
     *     fixedRateString 属性：fixedRate 的字符串形式。
     */

    @Scheduled(fixedRate = 2000)
    public void execute() {
        log.info("[execute][定时第 ({}) 次执行]", counts.incrementAndGet());
    }
}
