package api.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2020/5/13 17:03
 * @description
 */
@Data
@Component
@RefreshScope
@PropertySource("file:d:/test.properties")
@ConfigurationProperties(prefix = "test")
public class Property {
    String name;
}
