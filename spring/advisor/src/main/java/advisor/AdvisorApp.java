package advisor;

import org.springframework.aop.framework.ProxyConfig;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author payno
 * @date 2020/6/1 11:27
 * @description
 */
@SpringBootApplication
public class AdvisorApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context=new SpringApplicationBuilder(AdvisorApp.class)
                .web(WebApplicationType.NONE)
                .run(args);
        ProxyConfig config=context.getBean(ProxyConfig.class);
        /**
         *  AbstractAutoProxyCreator是实现代理的接口（抽象类）
         *  其子类对应JDK与AspectJ、或者其他代理
         *  Spring在启动时候会注入对应的代理器，然后在getBean（BeanPostProcessor）时进行代理
         *  @EnableAspectJAutoProxy会创建对应AspectJ的代理创建器
         */
        System.out.println(config.getClass());
    }
}
