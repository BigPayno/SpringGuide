package com.payno.webmvc.web.dto.esb;

import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * @author payno
 * @date 2019/12/19 10:04
 * @description
 */
@Data
@XmlRootElement(name = "Body")
@XmlAccessorType(XmlAccessType.FIELD)
/**
 * EsbXml的Body模块需要的Class类型
 */
@XmlSeeAlso({
        GjjCenterEsbRo.class,
        GjjCenterEsbVo.class
})
public class EsbBodyXo<B> {
    @XmlAnyElement(lax = true)
    private B body;
    public static <B> EsbBodyXo<B> body(B body){
        EsbBodyXo<B> esbBodyXo=new EsbBodyXo<>();
        esbBodyXo.setBody(body);
        return esbBodyXo;
    }
}
