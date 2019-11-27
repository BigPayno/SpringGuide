package com.payno.webmvc.web.exception;

import lombok.Getter;

/**
 * @author payno
 * @date 2019/11/26 16:15
 * @description
 */
@Getter
public class OtherException extends Exception{
    private ExceptionEnum response;
}
