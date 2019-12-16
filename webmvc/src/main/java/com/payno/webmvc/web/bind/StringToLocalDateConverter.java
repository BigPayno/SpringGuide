package com.payno.webmvc.web.bind;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author payno
 * @date 2019/12/16 08:50
 * @description
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    private static final DateTimeFormatter YYYY_MM_DD=DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
    @Override
    public LocalDate convert(String s) {

        return LocalDate.parse(s,YYYY_MM_DD);
    }
}
