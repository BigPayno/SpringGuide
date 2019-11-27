package com.payno.jpa.base.repo;

import com.payno.jpa.base.entity.inherit.FtpNode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author payno
 * @date 2019/11/27 16:00
 * @description
 */
public interface FtpNodeRepo extends JpaRepository<FtpNode,Integer> {
}
