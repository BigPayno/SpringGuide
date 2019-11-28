package com.payno.jpa.audit.provider;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author payno
 * @date 2019/11/28 09:36
 * @description
 */
@Component
public class AuditingNameProvider implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("payno");
    }
}
