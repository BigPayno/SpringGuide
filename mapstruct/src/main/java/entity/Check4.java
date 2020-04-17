package entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author payno
 * @date 2020/3/25 09:07
 * @description
 *      I like warrior
 */
@Data
@Builder
public class Check4 {
    private String cusName;
    private String idCard;
    private String mobile;
    private String channel;
}
