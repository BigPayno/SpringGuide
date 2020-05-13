package com.payno.jpa.data.common.example;

import com.payno.jpa.data.common.example.annotation.IgnoreMatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.ExampleMatcher;

import java.lang.reflect.Field;

/**
 * @author payno
 * @date 2020/5/13 09:34
 * @description
 */
@Slf4j
public class IgnoreResolver implements MatchResolver<IgnoreMatch> {
    @Override
    public void resolve(MatchContext context) {
        IgnoreMatch ignoreMatch = AnnotationUtils.findAnnotation(context.getClazz(),IgnoreMatch.class);
        if(ignoreMatch!=null){
            context.getExampleMatcher().withIgnoreCase(ignoreMatch.ignoreCase());
            log.debug("ExampleMatcher {} {}",ignoreMatch,ignoreMatch.ignoreCase());
        }
    }
}
