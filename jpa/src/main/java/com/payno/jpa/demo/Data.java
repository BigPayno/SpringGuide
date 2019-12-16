package com.payno.jpa.demo;

import javax.persistence.*;

/**
 * @author payno
 * @date 2019/11/28 11:32
 * @description
 */
@lombok.Data
@Entity
@Table(name = "DataModel")
public class Data extends BaseDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int type;
    private String name;

    @Override
    public String[] ignoreProperties() {
        return new String[]{"type"};
    }
}
