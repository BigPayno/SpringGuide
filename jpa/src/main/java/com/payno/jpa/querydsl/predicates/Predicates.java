package com.payno.jpa.querydsl.predicates;

import com.google.common.base.CaseFormat;
import com.google.common.base.Splitter;
import com.payno.jpa.querydsl.entity.QueryDsl;
import com.querydsl.core.types.dsl.DateTimePath;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author payno
 * @date 2019/11/28 15:37
 * @description
 */
public final class Predicates {
    private static final String QUERY_PREFIX = "Q";
    private static final String UPDATE_TIME_PATH = "updateTime";
    private static final Splitter CLASS_SPLITTER = Splitter.on(".");

    /**
     * 拿到Q类的Class
     */
    private static String queryClassName(Class<?> clazz) {
        String className = clazz.getName();
        List<String> columns = CLASS_SPLITTER.splitToList(className);
        int last = columns.size() - 1;
        return className.replace(columns.get(last), QUERY_PREFIX.concat(columns.get(last)));
    }

    private static DateTimePath<?> date(Class<?> clazz, String datePath) {
        try {
            Class<?> qClazz = Class.forName(queryClassName(clazz));
            String qObjectFieldName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, clazz.getSimpleName());
            Field qObjectField = qClazz.getDeclaredField(qObjectFieldName);
            Object qObject = qObjectField.get(null);
            Field qDateField = qClazz.getDeclaredField(UPDATE_TIME_PATH);
            return (DateTimePath<?>) qDateField.get(qObject);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 感觉太蠢了，不如手动
     */
    public static void main(String[] args) {
        DateTimePath dateTimePath = date(QueryDsl.class, "updateTime");
        dateTimePath.after(LocalDateTime.now().minusHours(3));
    }
}
