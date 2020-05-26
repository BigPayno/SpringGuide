package com.payno.webmvc.web.bind;

import com.alibaba.fastjson.support.spring.MappingFastJsonValue;
import com.payno.webmvc.controller.SecretController;
import com.payno.webmvc.web.annotation.Encryption;
import com.payno.webmvc.web.domain.Response;
import lombok.ToString;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;

/**
 * @author payno
 * @date 2019/12/20 11:51
 * @description
 *      类似的，Request
 */
@ToString
@RestControllerAdvice(basePackageClasses = SecretController.class)
public class SecretResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Encryption encryption=methodParameter.getMethod().getAnnotation(Encryption.class);
        return (encryption!=null);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        System.out.println(o.getClass());
        /**
         * 虽然过时了，但是没有简单的替换方法，将就呗
         */
        if(o instanceof MappingFastJsonValue){
            Object t=((MappingFastJsonValue) o).getValue();
            if(t instanceof Response){
                encryption(((Response) t).getT());
            }else{
                encryption(t);
            }
        }else if (o instanceof Response<?>){
            encryption(((Response) o).getT());
        }else{
            encryption(o);
        }
        return o;
    }

    private void encryption(Object object){
        for(Field field:object.getClass().getDeclaredFields()){
            Encryption encryption=field.getAnnotation(Encryption.class);
            if(encryption!=null){
                field.setAccessible(true);
                try{
                    String secret=(String) field.get(object);
                    char[] chars=new char[secret.length()];
                    secret.getChars(0,secret.length(),chars,0);
                    /**
                     * 获取加密的位数from，to
                     */
                    int from,to;
                    from=encryption.from()<0?0:encryption.from();
                    to=encryption.to()<secret.length()?
                            encryption.to()==-1?secret.length():encryption.to():
                            secret.length();
                    if(from<to){
                        for(int i=from;i<to;i++){
                            /**
                             * 脱敏数字
                             */
                            if(chars[i]>='0'&&chars[i]<='9'){
                                chars[i]=encryption.replace();
                            }
                        }
                    }
                    field.set(object,String.valueOf(chars));
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
