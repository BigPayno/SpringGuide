package com.payno.webmvc.web.domain;

import com.payno.webmvc.web.exception.ExceptionEnum;
import lombok.*;

import java.io.Serializable;

/**
 * @author payno
 * @date 2019/11/26 16:09
 * @description
 *      必须实现Getter,Setter，否则无法返回
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 8992436576262574064L;
    T t;
    int status;
    String message;
    public static Response<Void> of(ExceptionEnum exceptionEnum){
        return Response.<Void>builder()
                .status(exceptionEnum.getStatus()).message(exceptionEnum.getMessage()).build();
    }
    public static <T> Response<T> of(T t){
        return new Response<T>(t,200,"ok");
    }
    public static <T> Response<T> ok(){
        return new Response<T>(null,200,"ok");
    }
}
