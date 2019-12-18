package com.payno.webmvc.web.bind;

import com.google.common.io.ByteStreams;
import com.google.common.primitives.Bytes;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author payno
 * @date 2019/12/18 15:52
 * @description
 */
public class StringPaynoMessageConverter extends AbstractHttpMessageConverter<String> {
    public StringPaynoMessageConverter() {
        super(MediaTypes.PAYNO);
    }

    @Override
    public boolean supports(Class aClass) {
        return aClass.isAssignableFrom(String.class);
    }

    /**
     * 从request的流中读取
     */
    @Override
    public String readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream is=httpInputMessage.getBody();
        System.out.println("request head: payno");
        byte[] read=Bytes.concat("payno".getBytes(),ByteStreams.toByteArray(is));
        return new String(read);
    }

    /**
     * 返回response时
     */
    @Override
    public void writeInternal(String o, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String result=o.concat("payno");
        httpOutputMessage.getBody().write(result.getBytes());
    }
}
