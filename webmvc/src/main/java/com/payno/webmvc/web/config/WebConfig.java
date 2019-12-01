package com.payno.webmvc.web.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.payno.webmvc.web.bind.SecretReturnHandler;
import com.payno.webmvc.web.bind.UrlResolver;
import com.payno.webmvc.web.filter.TimeFilter;
import com.payno.webmvc.web.interceptor.LogAdapterInterceptor;
import com.payno.webmvc.web.interceptor.TimeInterceptor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Collections;
import java.util.List;

/**
 * @author payno
 * @date 2019/11/26 16:24
 * @description
 */
@Configuration
@Getter
public class WebConfig extends WebMvcConfigurationSupport {
    @Autowired
    TimeInterceptor timeInterceptor;
    @Autowired
    LogAdapterInterceptor logAdapterInterceptor;

    /**
     * WebFilter注册,也可以使用@WebFilter与@Component
     */
    @Bean
    public FilterRegistrationBean timeFilter() {
        TimeFilter timeFilter = new TimeFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(timeFilter);
        filterRegistrationBean.setUrlPatterns(
                Collections.singletonList("/time/*")
        );
        return filterRegistrationBean;
    }


    /**
     * Interceptor注册
     *
     * 实现:实现接口，继承Adaptor
     * 注意这里的pattern匹配方式与Filter不同
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor).excludePathPatterns("/**");
        registry.addInterceptor(logAdapterInterceptor);
    }

    /**
     * 配置controller方法参数解析器
     */
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UrlResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    /**
     * 可以配置静态资源访问
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        super.addResourceHandlers(registry);
    }

    /**
    *
     * addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。
     * allowedMethods：允许所有的请求方法访问该跨域资源服务器，如：POST、GET、PUT、DELETE等。
     * allowedOrigins：允许所有的请求域名访问我们的跨域资源，可以固定单条或者多条内容，
     * 如："http://www.baidu.com"，只有百度可以访问我们的跨域资源。
     * allowedHeaders：允许所有的请求header访问，可以自定义设置任意请求头信息，
     * 如："X-YAUTH-TOKEN"
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("GET");
        super.addCorsMappings(registry);
    }

    /**
     * 当存在JSON.class时，注入该converter
     */
    @Bean
    @ConditionalOnClass({JSON.class})
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteClassName
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
    /**
     * 配置将HttpMessage转换为java对象的转换器
     * 1. FormHttpMessageConverter
     *     支持 MultiValueMap 类型, 并且 MediaType 类型是 "multipart/form-data", 从 InputStream 里面读取数据, 并通过&符号分割, 最后转换成 MultiValueMap, 或 将 MultiValueMap转换成 & 符号连接的字符串, 最后转换成字节流, 输出到远端
     * 2. BufferedImageHttpMessageConverter
     *     支持 BufferedImgae 的 HttpMessageConverter, 通过 ImageReader 将 HttpBody 里面的数据转换成 BufferedImage, 或ImageWriter 将ImageReader 转换成字节流输出到 OutputMessage
     * 3. StringHttpMessageConverter
     *     支持数据是 String 类型的, 从 InputMessage 中读取指定格式的 str, 或 将数据编码成指定的格式输出到 OutputMessage
     * 4. SourceHttpMessageConverter
     *     支持 DOMSource, SAXSource, StAXSource, StreamSource, Source 类型的消息转换器, 在读取的时候, 从 HttpBody 里面读取对应的数据流转换成对应对应, 输出时通过 TransformerFactory 转换成指定格式输出
     * 5. ResourceHttpMessageConverter
     *     支持数据类型是 Resource 的数据, 从 HttpBody 中读取数据流转换成 InputStreamResource|ByteArrayResource, 或从 Resource 中读取数据流, 输出到远端
     * 6. ProtobufHttpMessageConverter
     *     支持数据类型是 com.google.protobuf.Message, 通过 com.google.protobuf.Message.Builder 将 HttpBody 中的数据流转换成指定格式的 Message, 通过 ProtobufFormatter 将 com.google.protobuf.Message 转换成字节流输出到远端
     * 7. ObjectToStringHttpMessageConverter
     *     支持 MediaType是 text/plain 类型, 从 InputMessage 读取数据转换成字符串, 通过 ConversionService 将字符串转换成自定类型的 Object; 或将 Obj 转换成 String, 最后 将 String 转换成数据流
     * 8. ByteArrayHttpMessageConverter
     *     支持格式是 byte 类型, 从 InputMessage 中读取指定长度的字节流, 或将 OutputMessage 转换成字节流
     * 9. AbstractXmlHttpMessageConverter及其子类
     *     支持从 xml 与 Object 之间进行数据转换的 HttpMessageConverter
     * 10. AbstractGenericHttpMessageConverter
     *     支持从 Json 与 Object 之间进行数据转换的 HttpMessageConverter (PS: 主要通过 JackSon 或 Gson)
     * 11. GsonHttpMessageConverter
     *     支持 application/*++json 格式的数据, 并通过 Gson, 将字符串转换成对应的数据
     * 12. MappingJackson2XmlHttpMessageConverter
     *     持 application/*++json/*+xml 格式的数据, 并通过 JackSon, 将字符串转换成对应的数据
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
    }

    @Override
    protected void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(new SecretReturnHandler());
        super.addReturnValueHandlers(returnValueHandlers);
    }
}
