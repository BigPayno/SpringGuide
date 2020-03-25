package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author payno
 * @date 2020/3/25 09:33
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubTarget {
    private Boolean result;
    private String name;
}