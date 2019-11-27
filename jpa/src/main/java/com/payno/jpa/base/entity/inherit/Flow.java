package com.payno.jpa.base.entity.inherit;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "Flow")
@NamedEntityGraph(name = "Flow.Graph", attributeNodes = {@NamedAttributeNode("nodes")})
public class Flow {
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "com.payno.jps.util.UUIDGenerator")
	private String flowId;
	private String flowCode;
	private String flowDefinition;
	private String localDate;
	private boolean needMessage;
	@OneToMany(mappedBy = "flowId")
	private Set<Node> nodes;
}
