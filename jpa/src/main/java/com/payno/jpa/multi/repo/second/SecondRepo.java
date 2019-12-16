package com.payno.jpa.multi.repo.second;

import com.payno.jpa.multi.entity.second.SecondModel;
import org.springframework.data.repository.CrudRepository;

/**
 * @author payno
 * @date 2019/12/5 14:33
 * @description
 */
public interface SecondRepo extends CrudRepository<SecondModel, Long> {
}
