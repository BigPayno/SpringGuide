package client;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author payno
 * @date 2019/12/26 09:29
 * @description
 */
@Slf4j
public class CuratorCrud {
    CuratorFramework template= CuratorFrameworkFactory.builder()
             .connectString("112.124.200.92:2181")
            .sessionTimeoutMs(60000)
            .connectionTimeoutMs(15000)
            .retryPolicy(new RetryOneTime(1000))
            .build();
    /**
     * create node data
     */
    @Test
    public void create() throws Exception{
        template.start();
        template.create().creatingParentsIfNeeded()
                /**
                 * 1、PERSISTENT--持久化目录节点
                 * 客户端与zookeeper断开连接后，该节点依旧存在
                 * 2、PERSISTENT_SEQUENTIAL-持久化顺序编号目录节点
                 * 客户端与zookeeper断开连接后，该节点依旧存在，只是Zookeeper给该节点名称进行顺序编号
                 * 3、EPHEMERAL-临时目录节点
                 * 客户端与zookeeper断开连接后，该节点被删除
                 * 4、EPHEMERAL_SEQUENTIAL-临时顺序编号目录节点
                 * 客户端与zookeeper断开连接后，该节点被删除，只是Zookeeper给该节点名称进行顺序编号
                 */
                .withMode(CreateMode.PERSISTENT)
                .forPath("/persist/a","chad".getBytes());
    }

    /**
     * ls
     * get
     */
    @Test
    public void get() throws Exception{
        template.start();
        template.getChildren().forPath("/").stream().forEach(System.out::println);
        Object value=template.getData().forPath("/persist/a");
        System.out.printf("val:[%s],type:[%s]%n",value,value.getClass());
        if(value instanceof byte[]){
            System.out.println(new String((byte[]) value));
        }
    }

    @Test
    public void set() throws Exception{
        template.start();
        template.setData().forPath("/persist/a","white".getBytes());
        byte[] val=template.getData().forPath("/persist/a");
        System.out.println(new String(val));
    }

    @Test
    public void delete() throws Exception{
        template.start();
        template.delete().guaranteed().deletingChildrenIfNeeded().forPath("/persist");
        template.getChildren().forPath("/").stream().forEach(System.out::println);
    }

    @Test
    public void zookeeper() throws Exception{
        template.start();
        template.getZookeeperClient().getZooKeeper();
    }

    @Test
    public void exist() throws Exception{
        template.start();
        Stat stat=template.checkExists().forPath("/zookeeper");
        if(stat==null){
            System.out.println("Node does not exist!");
        }else{
            System.out.println("Node Data Length: "+stat.getDataLength());
            System.out.println("Node Child Num: "+stat.getNumChildren());
        }
    }

    private void display(String path) throws Exception{
        Stat stat=template.checkExists().forPath(path);
        if(stat==null){
            log.warn("The Node[{}] does not exist!",path);
        }else {
            StringBuilder builder=new StringBuilder(String.format("The Node[%s]",path));
            if(stat.getDataLength()!=0){
                builder.append(
                        String.format(
                                " with [%s]",
                                new String(template.getData().forPath(path))
                                ));
            }
            System.out.println(builder.toString());
            if(stat.getNumChildren()!=0){
                List<String> childs=template.getChildren().forPath(path).stream().map(childPath->
                        path.equals("/")?Joiner.on("").join(path,childPath): Joiner.on("/").join(path,childPath)
                ).collect(Collectors.toList());
                for(String child:childs){
                    display(child);
                }
            }
        }
    }

    @Test
    public void displayAll() throws Exception{
        template.start();
        display("/");
    }

    @Test
    public void callback() throws Exception{
        template.start();
        template.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .inBackground((BackgroundCallback) (framework,event)->{
                    System.out.printf("create ephemeral node[%s] with data[%s]!",event.getPath(),event.getData());
                }).forPath("/persist/b","white".getBytes());
    }
}
