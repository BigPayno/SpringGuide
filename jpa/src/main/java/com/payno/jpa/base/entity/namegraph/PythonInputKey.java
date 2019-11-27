package com.payno.jpa.base.entity.namegraph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.function.Function;

/**
 * @author payno
 * @date 2019/7/11 15:12
 * @description 除了python名称外的参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Python_input_key")
@DynamicUpdate
@DynamicInsert
public class PythonInputKey {
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "com.gz.parser.core.utils.AssignedUUIDGenerator")
    private String inputKeyId;
    private String inputKeyName;
    private String pythonScriptId;

    public static Function<String, PythonInputKey> getPythonInputKey() {
        return s -> {
            return PythonInputKey.builder().inputKeyId(null).inputKeyName(s).build();
        };
    }
}
