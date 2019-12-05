package com.payno.jpa.multi.entity.second;

import lombok.Data;

import javax.persistence.*;

/**
 * @author payno
 * @date 2019/12/5 14:32
 * @description
 */
@Data
@Entity
@Table(name = "SecondModel")
public class SecondModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
