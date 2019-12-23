package com.payno.jpa.logicdelete.superclass;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author payno
 * @date 2019/12/23 09:07
 * @description
 */
@Data
@MappedSuperclass
public class LogicDelete implements Serializable {
    private static final long serialVersionUID = 5966306766659220492L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    protected String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedDate;

    protected String updatedBy;

    protected Boolean deleted = false;
}
