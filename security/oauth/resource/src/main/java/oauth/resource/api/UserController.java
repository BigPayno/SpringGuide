package oauth.resource.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author payno
 * @date 2020/5/20 15:49
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
