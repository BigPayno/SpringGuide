package com.payno.task.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author payno
 * @date 2020/3/24 09:51
 * @description
 */
@Profile("quartz")
@Slf4j
public class Job001 extends QuartzJobBean {

    private final AtomicInteger counts = new AtomicInteger();

    /**
     * JobExecutionContexts是一个可以访问的上下文文件
     * 存在键值对和Result以及运行时间等
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("[execute][定时第 ({}) 次执行]", counts.incrementAndGet());
    }
}
