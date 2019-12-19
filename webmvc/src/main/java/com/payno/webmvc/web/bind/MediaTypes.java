package com.payno.webmvc.web.bind;

import com.google.common.base.Charsets;
import org.springframework.http.MediaType;

/**
 * @author payno
 * @date 2019/12/18 16:04
 * @description
 */
public final class MediaTypes {
    public static MediaType PAYNO=new MediaType("application","payno", Charsets.UTF_8);
    public static MediaType FILE=new MediaType("application","file",Charsets.UTF_8);
}
