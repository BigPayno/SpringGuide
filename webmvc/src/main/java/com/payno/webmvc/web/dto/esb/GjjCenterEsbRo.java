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
@XmlRootElement(name="request")
public class GjjCenterEsbRo {
    @XmlElement
    private String cityNm;
    public static GjjCenterEsbRo of(String city){
        GjjCenterEsbRo ro=new GjjCenterEsbRo();
        ro.setCityNm(city);
        return ro;
    }
}
