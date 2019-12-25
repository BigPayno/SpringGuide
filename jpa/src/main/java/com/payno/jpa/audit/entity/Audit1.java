package com.payno.jpa.audit.entity;

import lombok.*;
import org.hibernate.validator.HibernateValidator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author payno
 * @date 2019/11/27 16:57
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Audit_001")
public class Audit1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "creation_time", nullable = false)
    @CreatedDate
    private LocalDateTime creationTime;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "modification_time")
    @LastModifiedDate
    private LocalDateTime modificationTime;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    /**
     * 乐观锁
     */
    @Version
    private long version;
}
