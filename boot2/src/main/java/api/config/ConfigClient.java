package api.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.context.properties.ConfigurationPropertiesRebinder;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author payno
 * @date 2020/5/14 09:47
 * @description
 */
@Configuration
public class ConfigClient implements ApplicationRunner ,ApplicationContextAware{

    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }

    @Autowired
    ConfigurationPropertiesRebinder configurationPropertiesRebinder;

    @Autowired
    ContextRefresher contextRefresher;

    @Override
    public void run(ApplicationArguments args){
        context.getBeansOfType(ApplicationListener.class).forEach((k,v)->
                System.err.println(k+" : "+v));
    }
}
