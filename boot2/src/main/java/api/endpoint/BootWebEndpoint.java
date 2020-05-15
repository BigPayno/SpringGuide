package api.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2020/5/11 11:39
 * @description
 */
@Slf4j
@Component
@WebEndpoint(id = "boot")
public class BootWebEndpoint {

    /**
     *  =>/actuator/boot/{name}
     */
    @ReadOperation
    public String get(@Selector String name){
        log.debug(name);
        return name;
    }
}
