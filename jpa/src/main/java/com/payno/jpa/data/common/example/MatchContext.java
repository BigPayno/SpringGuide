package com.payno.jpa.data.common.example;

import lombok.Data;
import org.springframework.data.domain.ExampleMatcher;

import java.lang.reflect.Field;

/**
 * @author payno
 * @date 2020/5/13 09:36
 * @description
 */
@Data
public class MatchContext {
    Class<?> clazz;
    ExampleMatcher exampleMatcher;
}
