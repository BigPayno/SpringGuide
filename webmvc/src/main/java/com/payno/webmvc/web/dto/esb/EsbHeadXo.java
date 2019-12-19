package com.payno.webmvc.web.dto.esb;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author payno
 * @date 2019/12/19 10:03
 * @description
 */
@Data
@XmlRootElement(name = "Head")
@XmlAccessorType(XmlAccessType.FIELD)
public class EsbHeadXo {
    private String serviceId;
    private String serviceName;
    public static EsbHeadXo template(){
        EsbHeadXo esbHeadXo=new EsbHeadXo();
        esbHeadXo.setServiceName("Nz_Risk");
        esbHeadXo.setServiceId("Nz001");
        return esbHeadXo;
    }
}
