package com.payno.task.quartzs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
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
@Profile("quartzs")
@Slf4j
@DisallowConcurrentExecution
/**
 *  注意，不是以 Quartz Job 为维度，保证在多个 JVM 进程中，有且仅有一个节点在执行，
 *  而是以 JobDetail 为维度。虽然说，绝大多数情况下，我们会保证一个 Job 和 JobDetail
 *  是一一对应。😈 所以，搞不清楚这个概念的胖友，最好搞清楚这个概念。实在有点懵逼，
 *  保证一个 Job 和 JobDetail 是一一对应就对了。
 *
 * 而 JobDetail 的唯一标识是 JobKey ，使用 name + group 两个属性。一般情况下，我们
 * 只需要设置 name 即可，而 Quartz 会默认 group = DEFAULT 。
 *
 * 不过这里还有一点要补充，也是需要注意的，在 Quartz 中，相同 Scheduler 名字的节点，
 * 形成一个 Quartz 集群。在下文中，我们可以通过 spring.quartz.scheduler-name 配置项，
 * 设置 Scheduler 的名字。
 *
 * 【重要】为什么要说这个呢？因为我们要完善一下上面的说法：通过在 Job 实现类上添加
 *  @DisallowConcurrentExecution 注解，实现在相同 Quartz Scheduler 集群中，相同 JobK
 * ey 的 JobDetail ，保证在多个 JVM 进程中，有且仅有一个节点在执行。
 */
public class Job001 extends QuartzJobBean {

    private final AtomicInteger counts = new AtomicInteger();

    /**
     *  会发现count总是1在数据库持久化模式下
     */

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("[execute][定时第 ({}) 次执行]", counts.incrementAndGet());
    }
}
