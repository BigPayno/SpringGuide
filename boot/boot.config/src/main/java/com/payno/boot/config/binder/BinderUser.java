package com.payno.boot.config.binder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/12/27 10:58
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties("binder.object.binder-user")
public class BinderUser {
    String name;
    String password;
}
