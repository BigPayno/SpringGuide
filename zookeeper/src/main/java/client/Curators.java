package client;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;

import java.util.concurrent.*;

/**
 * @author payno
 * @date 2019/12/26 10:33
 * @description
 */
public final class Curators {
    private Curators(){ }
    private static class Holder{
        private static CuratorFramework TEMPLATE= CuratorFrameworkFactory.builder()
                .connectString("112.124.200.92:2181")
                .sessionTimeoutMs(60000)
                .connectionTimeoutMs(15000)
                .retryPolicy(new RetryOneTime(1000))
                .build();
        private static ThreadPoolExecutor THREAD_POOL_EXECUTOR= new ThreadPoolExecutor(6,12,
                60L,TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
    }
    public static CuratorFramework client(){
        return Holder.TEMPLATE;
    }
    public static ExecutorService executor(){
        return Holder.THREAD_POOL_EXECUTOR;
    }
    public static void execute(Runnable runnable){
        Holder.THREAD_POOL_EXECUTOR.execute(runnable);
    }
}
