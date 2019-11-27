package com.payno.webmvc.web.bind;

import com.payno.webmvc.web.annotation.CurrentUrl;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author payno
 * @date 2019/11/27 11:21
 * @description
 */
public class UrlResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if(methodParameter.hasParameterAnnotation(CurrentUrl.class)){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        CurrentUrl currentUrl=methodParameter.getParameterAnnotation(CurrentUrl.class);

        return nativeWebRequest.getHeader("Host");

        /*//从Session 获取用户
        Object object = nativeWebRequest.getAttribute(currentUrl.sessionKey(), NativeWebRequest.SCOPE_SESSION);
        //从accessToken获得用户信息
        if (object == null) {
            String token = nativeWebRequest.getHeader(currentUrl.headerKey());
            if (token == null) {
                token = nativeWebRequest.getParameter(currentUrl.paramKey());
            }
            return "";
        }*/
    }
}
