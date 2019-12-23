package com.payno.jpa.logicdelete.annotation;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author payno
 * @date 2019/12/23 08:48
 * @description
 */
@Data
@Entity
@Table(name = "logic_delete_1")
@SQLDelete(sql = "update logic_delete_1 set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class LogicDelete {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="deleted")
    private Integer deleted = 0;

    private String userName;
}
