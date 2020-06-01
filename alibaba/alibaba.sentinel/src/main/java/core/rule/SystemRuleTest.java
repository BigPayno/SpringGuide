package core.rule;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.csp.sentinel.util.AppNameUtil;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import utils.Threads;

import java.util.stream.IntStream;

/**
 * @author payno
 * @date 2020/6/1 09:30
 * @description
 */
public class SystemRuleTest {
    @Before
    public void init(){
        SystemRule systemRule = new SystemRule();
        systemRule.setResource("Sys");
        systemRule.setMaxThread(2);
        //?
        SystemRuleManager.loadRules(Lists.newArrayList(systemRule));
    }

    @Test
    public void test() {
        IntStream.range(0,10).parallel().forEach(num->{
            try (Entry entry = SphU.asyncEntry("Sys")) {
                System.out.println(num);
                Threads.sleep(6000);
            } catch (BlockException ex) {
                if (SystemBlockException.isBlockException(ex)) {
                    System.out.println("并发过大！" + AppNameUtil.getAppName());
                }
            }
        });
        Threads.sleep(10000);
    }
}
