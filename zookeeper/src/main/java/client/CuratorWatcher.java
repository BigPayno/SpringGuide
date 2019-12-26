package client;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.listen.ListenerContainer;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;
import org.junit.Test;
import util.Threads;

import java.util.concurrent.ExecutorService;

/**
 * @author payno
 * @date 2019/12/26 10:37
 * @description
 *      Alpha
 */
public class CuratorWatcher {
    CuratorFramework client=Curators.client();
    ExecutorService executor=Curators.executor();
    @Before
    public void start() throws Exception{
        client.start();
        if(client.checkExists().forPath("/persist")==null){
            client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/persist");
        }
    }
    /**
     * 监视一个结点的创建、更新，并将结点的数据缓存在本地
     */
    @Test
    public void nodeCache() throws Exception{
        NodeCache nodeCache=new NodeCache(client,"/persist",false);
        ListenerContainer<NodeCacheListener> nodeWatcher=nodeCache.getListenable();
        nodeCache.start(true);
        nodeWatcher.addListener(()->{
            System.out.println(nodeCache.getCurrentData());
            if(nodeCache.getCurrentData()!=null&&nodeCache.getCurrentData().getData()!=null){
                System.out.printf("Data:[%s]%n%n",new String(nodeCache.getCurrentData().getData()));
            }
        },executor);
        executor.execute(()->{
            Threads.sleep(3000);
            System.out.println(Thread.currentThread().getName()+" try change the Node[/persist]!");
            try{
                /**
                 * 只更新了一次,猜测本地是轮询
                 */
                Threads.sleep(500);
                client.setData().forPath("/persist","payno,hello".getBytes());
                Threads.sleep(500);
                client.delete().deletingChildrenIfNeeded().forPath("/persist");
                Threads.sleep(500);
                client.create().creatingParentsIfNeeded().forPath("/persist");
                Threads.sleep(500);
                client.setData().forPath("/persist","payno".getBytes());
                Threads.sleep(500);
                client.delete().deletingChildrenIfNeeded().forPath("/persist");
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        Threads.sleep(Integer.MAX_VALUE);
    }

    /**
     * PathChildrenCache：监听子节点的新增、修改、删除操作。
     */
    @Test
    public void pathNodeCache(){

    }

    /**
     * TreeCache：既可以监听节点的状态，又可以监听子节点的状态。类似于上面两种Cache的组合。
     */
    @Test
    public void treeCache(){

    }
}
