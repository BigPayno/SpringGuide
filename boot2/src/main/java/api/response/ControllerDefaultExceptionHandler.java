package api.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author payno
 * @date 2019/11/26 17:00
 * @description
 */
@RestControllerAdvice
@Order(-2)
@Slf4j
public class ControllerDefaultExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result handleException(Exception e) {
        log.error("",e);
        return Result.of(ResultCode.UNKNOWN_EXCEPTION,e.getMessage());
    }
}
