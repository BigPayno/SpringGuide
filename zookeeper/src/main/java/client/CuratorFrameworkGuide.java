package client;

import com.google.common.base.Stopwatch;
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
 *      Recipes， ZooKeeper的系列recipe实现, 基于 Curator Framework.
 *      Framework， 封装了大量ZooKeeper常用API操作，降低了使用难度, 基于Zookeeper增加了一些新特性，对ZooKeeper链接的管理，对链接丢失自动重新链接。
 *      Utilities，一些ZooKeeper操作的工具类包括ZK的集群测试工具路径生成等非常有用，在Curator-Client包下org.apache.curator.utils。
 *      Client，ZooKeeper的客户端API封装，替代官方 ZooKeeper class，解决了一些繁琐低级的处理，提供一些工具类。
 *      Errors，异常处理, 连接异常等
 *      Extensions，对curator-recipes的扩展实现，拆分为 curator-:stuck_out_tongue_closed_eyes:iscovery和 curator-:stuck_out_tongue_closed_eyes:iscovery-server提供基于RESTful的Recipes WEB服务.
 */
public class CuratorFrameworkGuide {
    @Test
    public void builder(){
        org.apache.curator.framework.CuratorFramework framework=CuratorFrameworkFactory.builder()
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
