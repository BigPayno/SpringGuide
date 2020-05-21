package oauth.resource.api.resource;

/**
 * @author payno
 * @date 2020/5/19 15:26
 * @description
 */

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例模块 Controller
 */
@RestController
@RequestMapping("/api")
public class ExampleController {

    @RequestMapping("/hello")
    public String hello() {
        return "world";
    }

    @RequestMapping("world")
    public String world(){ return "hello";}

}
