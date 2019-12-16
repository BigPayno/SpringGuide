package com.payno.jpa.audit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author payno
 * @date 2019/11/27 16:57
 * @description 如果该类是MappedSuperclass注解的抽象类
 * 配合pre相关注解可以实现很多事情
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Audit_001")
public class Audit3 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "modification_time")
    private LocalDateTime modificationTime;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    /**
     * 乐观锁
     */
    @Version
    private long version;

    @PrePersist
    public void prePersist() {
        this.creationTime = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = LocalDateTime.now();
    }

    @PreRemove
    public void preRemove() {

    }
}
