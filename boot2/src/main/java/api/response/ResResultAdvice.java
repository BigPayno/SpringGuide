package api.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

/**
 * @author payno
 * @date 2020/5/8 16:04
 * @description
 */
@Slf4j
@RestControllerAdvice
@Order(-1)
public class ResResultAdvice implements ResponseBodyAdvice<Object> {

    List<ResResultWrapPreHandler> handlers;

    @PostConstruct
    public void init(){
        handlers = new LinkedList<>();
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ResponseAdvice responseAdvice = methodParameter.getMethod().getAnnotation(ResponseAdvice.class);
        if(responseAdvice!=null){
            return true;
        }
        ResponseAdvice responseAdvice1  = methodParameter.getMethod().getDeclaringClass().getAnnotation(ResponseAdvice.class);
        if(responseAdvice1!=null){
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof Result){
            log.debug("the response result has been wrapped!");
            return o;
        }else{
            log.debug("the response object has been wrapped to result!");
            handlers.forEach(handler->{
                if(handler.supports(o,methodParameter)){
                    log.debug("the response object has been handled by handler[{}]!",handler.getClass());
                    handler.preHandle(o);
                }
            });
            return Result.success(o);
        }
    }
}
