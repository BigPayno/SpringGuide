package com.payno.jpa.logicdelete.superclass;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author payno
 * @date 2019/12/23 09:11
 * @description
 */
@Data
@Entity
@Table(name = "logic_delete_demo")
public class Demo extends LogicDelete{
    public String demo;
}
