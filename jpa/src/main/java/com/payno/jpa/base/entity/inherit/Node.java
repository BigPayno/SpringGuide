package com.payno.jpa.base.entity.inherit;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Optional;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "Node")
@Inheritance(strategy = InheritanceType.JOINED)
public class Node {
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "com.payno.jpa.util.UUIDGenerator")
	private String nodeId;
	private String nodeName;
	private String nodeType;
	private long dateMinis;
	private String flowId;
	@Transient
	@JsonIgnore
	private String localDate;
	@Transient
	@JsonIgnore
	private Optional<String> source;
	@Transient
	@JsonIgnore
	private Optional<String> sink;
	@Transient
	@JsonIgnore
	private boolean noError;
	@Transient
	@JsonIgnore
	private boolean needMessage;
}
