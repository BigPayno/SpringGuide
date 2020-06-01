package core.rule;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import utils.Threads;

/**
 * @author payno
 * @date 2020/6/1 08:39
 * @description
 */
public class FlowRuleTest {

    /**
     *  resource    资源名，资源名是限流规则的作用对象
     *  count       限流阈值
     *  grade       限流阈值类型，QPS 或线程数模式
     *  QPS         模式
     *  limitApp    流控针对的调用来源 default，代表不区分调用来源
     *  strategy    判断的根据是资源自身，还是根据其它关联资源 (refResource)，还是根据链路入口 根据资源本身
     *  controlBehavior 流控效果（直接拒绝 / 排队等待 / 慢启动模式） 直接拒绝
     */


    @Before
    public void init(){
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(20);
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);
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
}
