package com.payno.jpa.demo;

import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * @author payno
 * @date 2019/11/28 10:29
 * @description
 */
public interface TimeFilter {
    boolean timeFilter(Predicate<LocalDateTime> predicate);
}
