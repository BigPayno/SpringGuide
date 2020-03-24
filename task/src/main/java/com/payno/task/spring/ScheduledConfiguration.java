package com.payno.task.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author payno
 * @date 2020/3/24 09:42
 * @description
 */
@Profile("spring")
@Configuration
@EnableScheduling
public class ScheduledConfiguration {
    /**
     *  1.在 spring.task.scheduling 配置项，Spring Task 调度任务的配置，对应 TaskSchedulingProperties 配置类。
     *  2.Spring Boot TaskSchedulingAutoConfiguration 自动化配置类，实现 Spring Task 的自动配置，创建 ThreadP
     *      oolTaskScheduler 基于线程池的任务调度器。本质上，ThreadPoolTaskScheduler 是基于 ScheduledExecutorSe
     *      rvice 的封装，增强在调度时间上的功能
     *  3.能够优雅关闭
     */
}
