package com.payno.webmvc.web.dto.esb;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hejun on 2018/5/21.
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="hsPblcCntrArray")
public class GjjCenterEsb {
    @XmlElement
    private String hsPblcCntrCd;
    @XmlElement
    private String hsPblcCntrNm;
}
