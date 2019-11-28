package com.payno.jpa.base.repo;

import com.payno.jpa.base.entity.inherit.Flow;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlowRepo extends JpaRepository<Flow,String> {
	 @EntityGraph(value = "Flow.Graph", type = EntityGraph.EntityGraphType.FETCH)
	 Optional<Flow> findByFlowCode(String flowCode);
}
