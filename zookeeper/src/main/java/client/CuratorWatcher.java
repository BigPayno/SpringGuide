package client;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.listen.ListenerContainer;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;
import org.junit.Test;
import util.Threads;

import java.util.concurrent.ExecutorService;

/**
 * @author payno
 * @date 2019/12/26 10:37
 * @description
 *      该部分也可以去做缓存Cache
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
    public void pathNodeCache() throws Exception{
        /**
         * cacheData决定event中data是否为null
         */
        PathChildrenCache pathChildrenCache=new PathChildrenCache(client,"/persist",true);
        pathChildrenCache.getListenable().addListener((template,event)->{
            ChildData childData=event.getData();
            String data=childData.getData()==null?null:new String(childData.getData());
            System.out.printf(
                    "ReceiveEvent(type=[%s],path=[%s],data=[%s],stat=[%s])%n",
                    event.getType(),childData.getPath(),data,childData.getStat());
            /**
             *      CHILD_ADDED,
             *      CHILD_UPDATED,
             *      CHILD_REMOVED,
             *      CONNECTION_SUSPENDED,
             *      CONNECTION_RECONNECTED,
             *      CONNECTION_LOST,
             *      INITIALIZED;
             *      只要一层监控，只能看到下一层子节点
             */
        });
        pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        executor.execute(()->{
            try{
                Threads.sleep(1000);
                client.create().creatingParentsIfNeeded().forPath("/persist/c/1");
                Threads.sleep(1000);
                client.setData().forPath("/persist/c","chad".getBytes());
                Threads.sleep(1000);
                client.setData().forPath("/persist/c/1","chad".getBytes());
                Threads.sleep(1000);
                client.delete().deletingChildrenIfNeeded().forPath("/persist/c");
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        Threads.sleep(Integer.MAX_VALUE);
    }

    /**
     * TreeCache：既可以监听节点的状态，又可以监听子节点(多层)的状态。
     */
    @Test
    public void treeCache() throws Exception{
        TreeCache treeCache=new TreeCache(client,"/persist");
        treeCache.getListenable().addListener((template,event)->{
            ChildData childData=event.getData();
            String data=childData.getData()==null?null:new String(childData.getData());
            System.out.printf(
                    "ReceiveTreeEvent(type=[%s],path=[%s],data=[%s],stat=[%s])%n",
                    event.getType(),childData.getPath(),data,childData.getStat());
        });
        treeCache.getUnhandledErrorListenable().addListener((str,throwable)->{
        });
        treeCache.start();
        executor.execute(()->{
            try{
                client.setData().forPath("/persist","payno".getBytes());
                Threads.sleep(500);
                client.create().creatingParentsIfNeeded().forPath("/persist/c/1");
                Threads.sleep(500);
                client.setData().forPath("/persist/c","chad".getBytes());
                Threads.sleep(500);
                client.setData().forPath("/persist/c/1","chad".getBytes());
                Threads.sleep(500);
                client.delete().deletingChildrenIfNeeded().forPath("/persist/c");
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        Threads.sleep(Integer.MAX_VALUE);
    }
}

