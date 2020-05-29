package guide;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.MoreExecutors;
import io.reactivex.Scheduler;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.concurrent.Executors;

/**
 * @author payno
 * @date 2020/5/29 10:01
 * @description
 */
public class SchedulerGuide {

    /**
     *  observeOn回调线程
     *  subscribeOn执行线程
     */

    @Test
    public void direct(){
        Threads.cost(()->{
            FastGuide.observable()
                    .observeOn(Schedulers.from(MoreExecutors.directExecutor()))
                    .subscribeOn(new ExecutorScheduler(
                            MoreExecutors.directExecutor()))
                    .subscribe(FastGuide.observer());
        });
        Threads.sleep(4000);
    }

    @Test
    public void executors(){
        Threads.cost(()->{
            FastGuide.observable()
                    .observeOn(new ExecutorScheduler(
                            MoreExecutors.directExecutor()))
                    .subscribeOn(new ExecutorScheduler(
                            Executors.newSingleThreadExecutor()))
                    .subscribe(FastGuide.observer());
        });
        Threads.sleep(4000);
    }

    @Test
    public void schedulers(){
        Threads.cost(()->{
            FastGuide.observable()
                    .observeOn(Schedulers.newThread())
                    .subscribeOn(new ExecutorScheduler(
                            MoreExecutors.directExecutor()))
                    .subscribe(FastGuide.observer());
        });
        Threads.sleep(4000);
    }
}
