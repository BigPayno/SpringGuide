package com.payno.jpa.multi.repo.primary;

import com.payno.jpa.multi.entity.primary.PrimaryModel;
import org.springframework.data.repository.CrudRepository;

/**
 * @author payno
 * @date 2019/12/5 14:33
 * @description
 */
public interface PrimaryRepo extends CrudRepository<PrimaryModel,Long> {
}
