package com.payno.jpa.querydsl.entity;

import com.payno.jpa.demo.BaseDataModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author payno
 * @date 2019/11/28 14:37
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Query_dsl")
public class QueryDsl extends BaseDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;

    @Override
    public String[] ignoreProperties() {
        return ExampleIgnoreConfig.QUERY_DSL;
    }
}
