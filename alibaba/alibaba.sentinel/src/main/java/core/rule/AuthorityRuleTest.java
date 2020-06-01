package core.rule;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.util.AppNameUtil;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

/**
 * @author payno
 * @date 2020/6/1 09:09
 * @description
 * ``该统计文件的命名方式是 ${appName}-metrics.log.${date} ，
 *  其中 ${appName} 会优先获取的系统参数 project.name 的值，
 *  如果获取不到会从启动参数中获取，具体的获取方式在 AppNameUtil 类中。
 *
 */
public class AuthorityRuleTest {
    @Before
    public void init(){
        AuthorityRule authorityRule = new AuthorityRule();
        authorityRule.setResource("Black");
        authorityRule.setStrategy(RuleConstant.AUTHORITY_BLACK);
        authorityRule.setLimitApp("App-A,App-B");
        AuthorityRuleManager.loadRules(Lists.newArrayList(authorityRule));
    }

    @Test
    public void test() {
        System.setProperty(AppNameUtil.APP_NAME,"App-A");
        try (Entry entry = SphU.entry("Black")) {
            System.out.println("允许访问"+ AppNameUtil.getAppName());
        } catch (BlockException ex) {
            if (AuthorityException.isBlockException(ex)) {
                System.out.println("禁止访问！" + AppNameUtil.getAppName());
            }
        }
    }
}
