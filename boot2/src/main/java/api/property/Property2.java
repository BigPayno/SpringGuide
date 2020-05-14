package api.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2020/5/13 17:16
 * @description
 */
@Data
@Component
@RefreshScope
public class Property2 {
    @Value("${test2.name}")
    String name;
}
