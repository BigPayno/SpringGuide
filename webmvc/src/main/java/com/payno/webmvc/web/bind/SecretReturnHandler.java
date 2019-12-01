package com.payno.webmvc.web.bind;

import com.payno.webmvc.web.annotation.Encryption;
import com.payno.webmvc.web.domain.Response;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Field;

/**
 * @author payno
 * @date 2019/11/29 09:20
 * @description
 */
public class SecretReturnHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        /**
         * 查看方法是否有Encryption注解
         */
        Encryption encryption=methodParameter.getMethod().getAnnotation(Encryption.class);
        System.out.println("ooo");
        return (encryption!=null);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        Object encryptionObject;
        if(o instanceof Response){
            Response<?> response=(Response) o;
            if(response.getT()!=null){
                encryptionObject=((Response) o).getT();
            }else{
                return;
            }
        }else{
            encryptionObject=o;
        }
        encryption(encryptionObject);
    }

    /**
     * 替换目标字符串
     */
    private void encryption(Object object){
        for(Field field:object.getClass().getDeclaredFields()){
            Encryption encryption=field.getAnnotation(Encryption.class);
            if(encryption!=null){
               if(encryption.from()==-1&&encryption.to()==-1){
                   field.setAccessible(true);
                   try{
                       String secret=(String) field.get(object);
                       char[] chars=new char[secret.length()];
                       secret.getChars(0,secret.length(),chars,0);
                       for(int i=0;i<secret.length();i++){
                           chars[i]=encryption.replace();
                       }
                       field.set(object,String.valueOf(chars));
                       System.out.println(String.valueOf(chars));
                   }catch (IllegalAccessException e){
                       e.printStackTrace();
                   }
               }
            }
        }
    }
}
