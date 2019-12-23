package com.payno.jpa.logicdelete.annotation;

import org.springframework.data.repository.CrudRepository;

/**
 * @author payno
 * @date 2019/12/23 08:53
 * @description
 */
public interface LogicDeleteRepo extends CrudRepository<LogicDelete,Long> {
}
