package com.payno.webmvc.web.dto.esb;

import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * @author payno
 * @date 2019/12/19 10:00
 * @description
 */
@Data
@XmlRootElement(name = "Service")
@XmlAccessorType(XmlAccessType.FIELD)
public class EsbXo {
    @XmlElement(name = "Head")
    private EsbHeadXo headXo;
    @XmlElement(name = "Body")
    private EsbBodyXo bodyXo;
    public static <T> EsbXo of(EsbHeadXo head, EsbBodyXo<T> body){
        EsbXo esbXo=new EsbXo();
        esbXo.setHeadXo(head);
        esbXo.setBodyXo(body);
        return esbXo;
    }
}
