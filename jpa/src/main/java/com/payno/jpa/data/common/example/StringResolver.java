package com.payno.jpa.data.common.example;

import com.payno.jpa.data.common.example.annotation.IgnoreMatch;
import com.payno.jpa.data.common.example.annotation.StringMatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.ExampleMatcher;

/**
 * @author payno
 * @date 2020/5/13 09:45
 * @description
 */
@Slf4j
public class StringResolver implements MatchResolver<StringMatch>{
    @Override
    public void resolve(MatchContext context) {
        StringMatch stringMatch = AnnotationUtils.findAnnotation(context.getClazz(),StringMatch.class);
        if(stringMatch!=null){
            context.getExampleMatcher().withStringMatcher(stringMatch.matcher());
            log.debug("ExampleMatcher {} {}",stringMatch.matcher(),context.clazz);
        }
    }
}
