package api.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author payno
 * @date 2020/5/8 15:51
 * @description
 */
@Data
@Accessors(chain = true)
public class Result {
    Integer code;
    String msg;
    Object data;
    public static Result of(Integer code,String msg,Object data){
        return new Result().setCode(code).setMsg(msg).setData(data);
    }
    public static Result of(ResultCode resultCode,Object data){
        return new Result().setCode(resultCode.code).setMsg(resultCode.msg).setData(data);
    }
    public static Result success(){
        return Result.of(ResultCode.SUCCESS,null);
    }
    public static Result success(Object data){
        return Result.of(ResultCode.SUCCESS,data);
    }
    public static Result of(HttpCodeHolder httpCodeHolder){
        return Result.of(httpCodeHolder.resultCode(),null);
    }
}
