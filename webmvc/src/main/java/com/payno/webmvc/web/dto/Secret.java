package com.payno.webmvc.web.dto;

import com.payno.webmvc.web.annotation.Encryption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author payno
 * @date 2019/11/29 09:17
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Secret {
    @Encryption(from = 2,to = 4)
    private String psd;
}
