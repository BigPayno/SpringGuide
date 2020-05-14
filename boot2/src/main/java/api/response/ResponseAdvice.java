package api.response;

import java.lang.annotation.*;

/**
 * @author payno
 * @date 2020/5/8 16:06
 * @description
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseAdvice {
}
