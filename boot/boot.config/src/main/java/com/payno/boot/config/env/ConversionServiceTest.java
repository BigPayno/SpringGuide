package com.payno.boot.config.env;

import org.junit.Test;
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
 */
public class ConversionServiceTest {
    @Test
    public void defaultService(){
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
}
