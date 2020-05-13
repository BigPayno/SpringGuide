package com.payno.jpa.data.common.entity;

import com.payno.jpa.data.common.example.MatchResolvers;
import com.payno.jpa.data.common.example.annotation.IgnoreMatch;
import com.payno.jpa.data.common.example.annotation.StringMatch;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author payno
 * @date 2020/5/13 09:13
 * @description
 */
@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@IgnoreMatch(ignoreCase = {"id","nativeData","createTime","updateTime"})
@StringMatch(matcher = ExampleMatcher.StringMatcher.CONTAINING)
public abstract class AbstractData<T extends AbstractData<T>> implements Data<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String idCard;
    private String mobile;
    private String name;
    private String channel;
    private String nativeData;
    @CreatedDate
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime updateTime;

    @Override
    public Example<T> toExample() {
        return MatchResolvers.resolve(get());
    }
}
