package com.payno.jpa.multi.entity.primary;

import lombok.Data;

import javax.persistence.*;

/**
 * @author payno
 * @date 2019/12/5 14:32
 * @description
 */
@Data
@Entity
@Table(name = "PrimaryModel")
public class PrimaryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
