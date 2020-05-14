package api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author payno
 * @date 2020/5/8 15:54
 * @description
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS(001,"成功"),
    UNKNOWN_EXCEPTION(1000,"未知错误"),
    PARAM_IS_VALID(1001,"参数无效");
    Integer code;
    String msg;
}
