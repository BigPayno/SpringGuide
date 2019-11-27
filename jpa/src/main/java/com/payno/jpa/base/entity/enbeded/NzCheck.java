package com.payno.jpa.base.entity.enbeded;

import lombok.*;

import javax.persistence.*;

/**
 * @author payno
 * @date 2019/9/3 15:25
 * @description
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Check")
@SuppressWarnings("unchecked")
public class NzCheck implements DataAccess {
    @EmbeddedId
    private RowKeyPK rowKeyPK;
    @Embedded
    private Check4 check4;
    @Column(updatable = false, insertable = false)
    private long timestamp;
    private String fake;
    private String response;
}
