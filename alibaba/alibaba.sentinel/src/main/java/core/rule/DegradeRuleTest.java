package core.rule;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

/**
 * @author payno
 * @date 2020/6/1 09:43
 * @description
 */
public class DegradeRuleTest {
    @Before
    public void init(){
        DegradeRule rule = new DegradeRule();
        rule.setResource("Degrade");
        /**
         *  滑动窗口，异常数，异常比例
         */
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        rule.setRtSlowRequestAmount(2);
        rule.setTimeWindow(1);
        DegradeRuleManager.loadRules(Lists.newArrayList(rule));
    }

    @Test
    public void test() {
        while(true){
            // 1.5.0 版本开始可以直接利用 try-with-resources 特性
            try (Entry entry = SphU.entry("Degrade")) {
                // 被保护的逻辑
                System.out.println("Degrade");
            } catch (BlockException ex) {
                // 处理被流控的逻辑
                Tracer.trace(ex);
            }
        }
    }
}
