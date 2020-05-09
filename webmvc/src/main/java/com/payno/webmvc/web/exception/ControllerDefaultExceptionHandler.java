package com.payno.webmvc.web.exception;

import com.payno.webmvc.web.domain.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author payno
 * @date 2019/11/26 17:00
 * @description
 */
@RestControllerAdvice
@Order(1)
@Slf4j
public class ControllerDefaultExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<?> handleException(Exception e) {
        log.error("",e);
        return Response.of(ExceptionEnum.UNKNOWN_EXCEPTION);
    }
}
