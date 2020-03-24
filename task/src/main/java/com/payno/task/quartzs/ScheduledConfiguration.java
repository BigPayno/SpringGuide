package com.payno.task.quartzs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author payno
 * @date 2020/3/24 09:42
 * @description
 */
@Profile("quartzs")
@Configuration
@EnableScheduling
public class ScheduledConfiguration {

    /**
     *  1.在 spring.quartz 配置项，Quartz 的配置，对应 QuartzProperties 配置类。
     *  2.Spring Boot QuartzAutoConfiguration 自动化配置类，实现 Quartz 的自动配置，创建 Quartz Scheduler(调度器) Bean 。
     */

    public static class Job01Config{
        @Bean
        public JobDetail job001Detail(){
            return JobBuilder.newJob(Job001.class)
                    .withIdentity("Job001") // 名字为 demoJob01
                    .storeDurably() // 没有 Trigger 关联的时候任务是否被保留。因为创建 JobDetail 时，还没 Trigger 指向它，所以需要设置为 true ，表示保留。
                    .build();
        }

        @Bean
        public Trigger job001Trigger(){
            // 简单的调度计划的构造器
            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(5) // 频率。
                    .repeatForever(); // 次数。
            // Trigger 构造器
            return TriggerBuilder.newTrigger()
                    .forJob(job001Detail()) // 对应 Job 为 demoJob01
                    .withIdentity("job001Trigger") // 名字为 demoJob01Trigger
                    .withSchedule(scheduleBuilder) // 对应 Schedule 为 scheduleBuilder
                    .build();
        }
    }
    public static class Job02Config{
        @Bean
        public JobDetail job002Detail(){
            return JobBuilder.newJob(Job002.class)
                    .withIdentity("Job002") // 名字为 demoJob01
                    .storeDurably() // 没有 Trigger 关联的时候任务是否被保留。因为创建 JobDetail 时，还没 Trigger 指向它，所以需要设置为 true ，表示保留。
                    .build();
        }

        @Bean
        public Trigger job002Trigger(){
            // 基于 Quartz Cron 表达式的调度计划的构造器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ? *");
            // Trigger 构造器
            return TriggerBuilder.newTrigger()
                    .forJob(job002Detail()) // 对应 Job 为 demoJob01
                    .withIdentity("job002Trigger") // 名字为 demoJob01Trigger
                    .withSchedule(scheduleBuilder) // 对应 Schedule 为 scheduleBuilder
                    .build();
        }
    }
}
