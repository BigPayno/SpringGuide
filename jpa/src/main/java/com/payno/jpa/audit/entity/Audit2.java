package com.payno.jpa.audit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
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
@Table(name = "Audit_002")
public class Audit2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description", length = 500)
    private String description;

    @CreatedBy
    private String createBy;

    @LastModifiedBy
    private String updateBy;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    /**
     * 乐观锁
     */
    @Version
    private long version;
}
