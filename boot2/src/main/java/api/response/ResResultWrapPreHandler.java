package api.response;

import org.springframework.core.MethodParameter;

import java.lang.annotation.Annotation;

/**
 * @author payno
 * @date 2020/5/8 16:16
 * @description
 */
public interface ResResultWrapPreHandler extends Comparable{
    boolean supports(Object t,MethodParameter methodParameter);
    Object preHandle(Object t);
}
