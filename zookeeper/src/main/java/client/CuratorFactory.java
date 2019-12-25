package client;

import com.google.common.base.Stopwatch;
import com.google.common.primitives.Bytes;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorTempFramework;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.utils.CloseableUtils;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author payno
 * @date 2019/12/25 16:43
 * @description
 */
public class CuratorFactory {
    @Test
    public void builder(){
        CuratorFramework framework=CuratorFrameworkFactory.builder()
                .connectString("112.124.200.92:2181")
                .sessionTimeoutMs(60000)
                .connectionTimeoutMs(15000)
                .retryPolicy(new RetryOneTime(1000))
                .build();
        try{
            Stopwatch stopwatch=Stopwatch.createStarted();
            framework.start();
            framework.blockUntilConnected();
            System.out.printf("Curator connect zookeeperServer in [%s]",stopwatch.stop());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CloseableUtils.closeQuietly(framework);
        }
    }


    /**
     * buildTemp()创建创建CuratorTempFramework然后在它不用三分钟后就关闭它。
     */
    @Test
    public void temp(){
        CuratorTempFramework framework=CuratorFrameworkFactory.builder()
                .connectString("112.124.200.92:2181")
                .sessionTimeoutMs(60000)
                .connectionTimeoutMs(15000)
                .retryPolicy(new RetryOneTime(1000))
                .buildTemp(10, TimeUnit.SECONDS);
        try{
            Stopwatch stopwatch=Stopwatch.createStarted();
            byte[] data=framework.getData().forPath("/zookeeper");
            System.out.println(new String(data));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CloseableUtils.closeQuietly(framework);
        }
    }
}
