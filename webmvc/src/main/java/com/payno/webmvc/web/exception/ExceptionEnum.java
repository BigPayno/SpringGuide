package com.payno.webmvc.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author payno
 * @date 2019/11/26 16:12
 * @description
 */
@AllArgsConstructor
@Getter
public enum ExceptionEnum{
    HTTP_EXCEPTION(000,"http exception"),
    PARAM_ERROR(001,"param error"),
    UNKNOWN_EXCEPTION(999,"unknown exception,please look the log!");
    private int status;
    private String message;
}
