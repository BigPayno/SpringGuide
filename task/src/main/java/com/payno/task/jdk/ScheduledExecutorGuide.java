package com.payno.task.jdk;

import com.payno.task.utils.Threads;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author payno
 * @date 2020/3/24 09:30
 * @description
 *      1.推荐使用ScheduledExecuteService而不是TimeTask
 */
@Slf4j
public class ScheduledExecutorGuide {
    static ScheduledExecutorService scheduledExecutor= Executors.newScheduledThreadPool(4);
    Runnable task= ()->{
      log.info("[{}] start execute task",Thread.currentThread().getName());
      Threads.sleep(1000);
      log.info("[{}] finished execute task",Thread.currentThread().getName());
    };
    Runnable task2= ()->{
        log.error("something wrong!");
      throw new RuntimeException();
    };
    void async(){
        scheduledExecutor.schedule(task2,1500,TimeUnit.MILLISECONDS);
        scheduledExecutor.scheduleAtFixedRate(task,0,1, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        ScheduledExecutorGuide guide=new ScheduledExecutorGuide();
        guide.async();
    }
}
