package com.payno.task.quartzs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2020/3/24 09:51
 * @description
 */
@Profile("quartzs")
@Slf4j
@DisallowConcurrentExecution
public class Job002 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("[execute][开始执行]");
    }
}
