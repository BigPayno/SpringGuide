package com.payno.webmvc.web.exception;

import com.google.common.base.Splitter;
import com.payno.webmvc.web.domain.Response;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

/**
 * @author payno
 * @date 2019/11/26 16:07
 * @description
 */
@RestControllerAdvice
@Order(-2)
public class ControllerExceptionHandler {
    public static final Splitter PATH_SPLITTER=Splitter.on('.');
    @ExceptionHandler(OtherException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<?> handleUserNotExistsException(OtherException e) {
        return Response.of(e.getResponse());
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return FebsResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String pathArr =PATH_SPLITTER.limit(2).split(path.toString()).iterator().next();
            message.append(pathArr).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return message.toString();
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return FebsResponse
     */
    @ExceptionHandler(BindException.class)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> validExceptionHandler(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return Response.builder().status(ExceptionEnum.PARAM_ERROR.getStatus()).message(message.toString()).build();

    }
}
