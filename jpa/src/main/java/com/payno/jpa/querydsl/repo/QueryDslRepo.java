package com.payno.jpa.querydsl.repo;

import com.payno.jpa.querydsl.entity.QueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author payno
 * @date 2019/11/28 14:41
 * @description
 */
public interface QueryDslRepo extends JpaRepository<QueryDsl, Long>, QuerydslPredicateExecutor<QueryDsl> {
}
