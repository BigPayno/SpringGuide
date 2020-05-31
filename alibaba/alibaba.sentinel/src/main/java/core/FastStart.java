package core;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.Env;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import utils.Threads;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @author payno
 * @date 2020/5/29 17:00
 * @description
 *      Entry进入close方法会更新信号量
 *      但是SphU并不适用于多线程，所以
 */
public class FastStart {

    @Before
    public void init(){
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20. 限流
        rule.setCount(20);
        FlowRuleManager.loadRules(Lists.newArrayList(rule));
    }

    @Test
    public void test() {
        while(true){
            // 1.5.0 版本开始可以直接利用 try-with-resources 特性
            try (Entry entry = SphU.entry("HelloWorld")) {
                // 被保护的逻辑
                System.out.println("hello world");
            } catch (BlockException ex) {
                // 处理被流控的逻辑
                Threads.sleep(1000);
                System.out.println("blocked!");
            }
        }
    }

    @Test
    public void test2() {
        IntStream.range(0,50).forEach(val->{
            try (Entry entry = SphU.entry("HelloWorld")) {
                new Thread(()->{
                    Threads.PrintStream.Out.println("hello world");
                },Integer.valueOf(val).toString()).start();
            } catch (BlockException ex) {
                // 处理被流控的逻辑
                Threads.sleep(1000);
                Threads.PrintStream.Err.println("error");
            }
        });
        Threads.sleep(10000);
    }

    @Test
    /**
     * 每次使用Entry都使信号量发生变化，后面测试得出是创建触发信号变更的
     */
    public void test3(){
        while(true){
            try(Entry entry=SphU.entry("HelloWorld")){
            }catch (BlockException e){
                break;
            }
        }
        System.out.println("break!");
    }

    @Test
    /**
     * 多线程里的Entry失效了？
     */
    public void test4() {
        IntStream.range(0,50).forEach(val->{
            new Thread(()->{
                // 1.5.0 版本开始可以直接利用 try-with-resources 特性
                try (Entry entry = SphU.entry("HelloWorld")) {
                    // 被保护的逻辑
                    Threads.PrintStream.Out.println("hello world");
                } catch (BlockException ex) {
                    // 处理被流控的逻辑
                    Threads.sleep(1000);
                    Threads.PrintStream.Err.println("error");
                }
            },Integer.valueOf(val).toString()).start();
        });
        Threads.sleep(10000);
    }

    @Test
    /**
     * 多线程和单线程创建的Entry都是不同的
     * 使用的Env.sph是单例的
     */
    public void test5(){
        Map<Entry,Boolean> map = new ConcurrentHashMap<>();
        IntStream.range(0,10).forEach(val->{
            new Thread(()->{
                try (Entry entry = SphU.entry("HelloWorld")) {
                    map.put(entry,Boolean.TRUE);
                    System.out.println(Env.sph);
                } catch (BlockException ex) { }
            },Integer.valueOf(val).toString()).start();
        });
        Threads.sleep(1000);
        map.keySet().forEach(System.out::println);

        try{
            Entry entry1 = SphU.entry("HelloWorld");
            Entry entry2 = SphU.entry("HelloWorld");
            System.out.println(entry1);
            System.out.println(entry2);
            System.out.println(Env.sph);
        }catch (BlockException e){}
    }

    /**
     *  信号量的触发在创建时，而非close时
     */
    @Test
    public void test6(){
        try{
            for(int i=0;i<30;i++){
                SphU.entry("HelloWorld");
            }
        }catch (BlockException e){
            System.out.println("close add sem！");
        }
        System.out.println("no close no sem！");

        try{
            for(int i=0;i<30;i++){
                SphU.entry("HelloWorld").close();
            }
        }catch (BlockException e){
            System.out.println("close add sem！");
        }
    }
}
