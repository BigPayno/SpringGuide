package api.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2020/5/14 09:08
 * @description
 *      RefreshEndpoint->ContextRefresher->EnvironmentChangeEvent
 *      =>LoggingRe binder(更改日志级别)/ConfigurationPropertiesRe binder(@ConfigurationProperties/@Value等)
 *
 *      ContextRefresher
 *      =>refresh
 *      利用Spring（包含两个监听器）重新加载Environment然后对比
 *
 */
@Slf4j
@Component
public class RefreshListener implements ApplicationListener<EnvironmentChangeEvent> {
    @Override
    public void onApplicationEvent(EnvironmentChangeEvent environmentChangeEvent) {
        log.info("env change event happened!");
        environmentChangeEvent.getKeys().forEach(System.err::println);
    }
}
