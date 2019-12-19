package com.payno.webmvc.web.dto.esb;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by hejun on 2018/5/21.
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="response")
public class GjjCenterEsbVo {
    @XmlElement
    List<GjjCenterEsb> hsPblcCntrArray;
}
