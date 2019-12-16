package com.payno.jpa.demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author payno
 * @date 2019/11/28 11:51
 * @description
 */
public interface DataRepo extends JpaRepository<Data, Long> {
}
