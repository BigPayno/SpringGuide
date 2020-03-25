package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author payno
 * @date 2020/3/25 09:31
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Source {
    private String id;
    private Integer num;
    private Integer totalCount;
    private SubSource subSource;
}
