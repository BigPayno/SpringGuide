package com.payno.jpa.demo;

/**
 * @author payno
 * @date 2019/11/28 10:35
 * @description
 */
public interface NativeResponseAccessor {
    void setNativeResponse(String response);
    String getNativeResponse();
}
