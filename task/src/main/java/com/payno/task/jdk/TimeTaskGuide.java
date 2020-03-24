package com.payno.task.jdk;

import com.payno.task.utils.Threads;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author payno
 * @date 2020/3/24 09:20
 * @description
 */
@Slf4j
public class TimeTaskGuide {
    static Timer timer=new Timer();
    static TimerTask task1 = new TimerTask() {
        @Override
        public void run() {
            log.info("task start execute!");
            Threads.sleep(2000);
            log.info("task finished execute!");
        }
    };
    static TimerTask task2 = new TimerTask() {
        @Override
        public void run() {
            throw new RuntimeException();
        }
    };

    void sync(){
        timer.schedule(task1,0,1000);
        timer.schedule(task2,1500);
    }

    public static void main(String[] args) {
        /**
         * 1.执行是串行的，必须等待上一个执行完毕才会执行下一个
         * 2.执行发生异常，会导致整个线程后续执行的任务执行失败
         */
        TimeTaskGuide guide=new TimeTaskGuide();
        guide.sync();
    }
}
