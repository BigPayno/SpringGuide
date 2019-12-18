package com.payno.webmvc.web.bind;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author payno
 * @date 2019/12/16 08:50
 * @description
 *      Convert/Format机制与HttpMessageConverter的关系
 *      其中HttpMessageConverter负责解析http的body部分(Body)
 *      Convert/Format SPI负责解析其他部分（URL/Header/Parameter）
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    private static final DateTimeFormatter YYYY_MM_DD=DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
    @Override
    public LocalDate convert(String s) {

        return LocalDate.parse(s,YYYY_MM_DD);
    }
}
