package oauth.resource.api;

/**
 * @author payno
 * @date 2020/5/19 15:26
 * @description
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例模块 Controller
 */
@RestController
@RequestMapping("/api")
public class ExampleController {

    @RequestMapping("/hello")
    //@PreAuthorize("hasRole('ROLE_CLIENT_TRUST')")
    public String hello() {
        return "world";
    }

}
