package api.env;

import api.WebApiApplication;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.cloud.bootstrap.BootstrapApplicationListener;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author payno
 * @date 2020/5/14 10:08
 * @description
 */
public class Environments {

    @Configuration(
            proxyBeanMethods = false
    )
    protected static class Empty {
        protected Empty() {
        }
    }

    public static Environment getCloud(){
        StandardEnvironment environment=new StandardEnvironment();
        SpringApplicationBuilder builder = (new SpringApplicationBuilder(new Class[]{Empty.class}))
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .environment(environment);
        builder.application().setListeners(Arrays.asList(new BootstrapApplicationListener(), new ConfigFileApplicationListener()));
        builder.build(new String[0]);
        return environment;
    }

    public static Environment getBoot(){
        StandardEnvironment environment=new StandardEnvironment();
        SpringApplicationBuilder builder = (new SpringApplicationBuilder(new Class[]{Empty.class}))
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .environment(environment);
        builder.application().setListeners(Arrays.asList(new ConfigFileApplicationListener()));
        builder.build(new String[0]);
        return environment;
    }

    public static Environment getBoot(Class<?> context) throws IOException {
        StandardEnvironment environment=new StandardEnvironment();
        MutablePropertySources mutablePropertySources=new MutablePropertySources();
        YamlPropertySourceLoader loader=new YamlPropertySourceLoader();
        List<PropertySource<?>> propertySources=loader.load("yml", new FileSystemResource("D:\\test\\SpringGuide\\boot2\\src\\main\\resources\\application.yml"));
        for(PropertySource yml:propertySources){
            mutablePropertySources.addLast(yml);
        }
        mutablePropertySources.forEach(environment.getPropertySources()::addLast);
        SpringApplicationBuilder builder = (new SpringApplicationBuilder(new Class[]{context}))
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .environment(environment);
        builder.application().setListeners(Arrays.asList(new ConfigFileApplicationListener()));
        builder.build(new String[0]);
        return environment;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(getBoot(WebApiApplication.class));
    }
}
