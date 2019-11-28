package com.payno.jpa.base.entity.namegraph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author payno
 * @date 2019/7/11 10:53
 * @description python脚本
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Python_script")
@DynamicUpdate
@DynamicInsert
@NamedEntityGraph(name = "PythonScript.Graph", attributeNodes = {@NamedAttributeNode("pythonInputKeys")})
public class PythonScript {
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "com.payno.jpa.util.UUIDGenerator")
    private String pythonScriptId;
    private String pythonScriptName;
    private String pythonScriptDescription;
    @OneToMany(mappedBy = "pythonScriptId")
    private List<PythonInputKey> pythonInputKeys;
}