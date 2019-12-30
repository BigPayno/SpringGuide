package com.payno.boot.config.env;

import com.payno.boot.config.env.examples.UserStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author payno
 * @date 2019/12/27 17:04
 * @description
 *      ConfigurableEnvironment implements ConfigurablePropertyResolver,Environment
 *          <>---->ConversionService[Converter/Format SPI]
 *
 *      1.Spring使用ConversionService来convert各种类型.默认提供的是DefaultConversionService.
 * 同时它实现了ConverterRegistry接口,所以也可以添加你自定义的converter.
 *      2.Spring提供了3种converter接口,分别是Converter,ConverterFactory和GenericConverter.一
 * 般用于1:1, 1:N, N:N的source->target类型转化.
 *      3.在DefaultConversionService内部3种converter都会转化成GenericConverter放到静态内部
 * 类Converters中.
 *      4.接口ConvertiblePair是source的class与target的Class的封装.静态内部类ConvertersForPai
 * r是多个converter对应的LinkedList的封装..静态内部类Converters中含有1个Map<ConvertiblePair, ConvertersForPair>用来储存所有converter.
 *      1个GenericConverter可以对应N个ConvertiblePair,1个ConvertiblePair对应的ConvertersFor
 * Pair中也可以有N个GenericConverter.
 *
 */
public class ConversionServiceTest {
    @Test
    public void converter(){
        DefaultConversionService service=new DefaultConversionService();
        boolean canConvert=service.canConvert(String.class, LocalDate.class);
        service.addConverter(new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String s) {
                return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyyMMdd"));
            }
        });
        System.out.println(canConvert);
        if(service.canConvert(String.class,LocalDate.class)){
            LocalDate localDate=service.convert("20121111",LocalDate.class);
            System.out.println(localDate);
        }
    }

    @Test
    public void converterFactory(){
        ConversionService conversionService=new DefaultConversionService();
        /**
         * StringToEnumConverter implements ConverterFactory<String,Enum>
         */
        UserStatus userStatus=conversionService.convert("LOGIN_STATUS", UserStatus.class);
        System.out.println(userStatus.getType());
    }
}
