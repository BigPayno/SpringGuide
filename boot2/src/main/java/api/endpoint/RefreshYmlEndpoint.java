package api.endpoint;

import api.WebApiApplication;
import api.env.Environments;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author payno
 * @date 2020/5/14 10:29
 * @description
 */
@Slf4j
@Component
@WebEndpoint(id = "refreshYml")
public class RefreshYmlEndpoint implements ApplicationContextAware{

    private static final String DEFAULT_CONFIG="applicationConfig: [classpath:/application.yml]";

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    Environment environment;

    @Autowired
    RefreshScope refreshScope;

    @Autowired
    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }

    @ReadOperation
    public Set<String> refresh() throws IOException {
        Set<String> changeKeys = changeKeys();
        eventPublisher.publishEvent(new EnvironmentChangeEvent(context, changeKeys));
        refreshScope.refreshAll();
        return changeKeys;
    }

    Set<String> changeKeys() throws IOException{
        YamlPropertySourceLoader loader=new YamlPropertySourceLoader();
        List<PropertySource<?>> propertySources=loader.load(DEFAULT_CONFIG, new FileSystemResource("D:\\test\\SpringGuide\\boot2\\src\\main\\resources\\application.yml"));
        StandardEnvironment curEnv = (StandardEnvironment)environment;
        curEnv.getPropertySources().get(DEFAULT_CONFIG);
        curEnv.getPropertySources().remove(DEFAULT_CONFIG);
        curEnv.getPropertySources().addLast(propertySources.get(0));
        return Sets.newHashSet("test2.name");
    }
}
