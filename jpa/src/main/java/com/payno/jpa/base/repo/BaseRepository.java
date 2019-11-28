package com.payno.jpa.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author payno
 * @date 2019/11/28 10:12
 * @description
 */
@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T,ID>{

}
