package com.payno.jpa.audit.provider;

import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

/**
 * @author payno
 * @date 2019/11/27 16:53
 * @description
 */
@Component
public class AuditingDateTimeProvider implements DateTimeProvider {
    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(LocalDateTime.now());
    }
}
