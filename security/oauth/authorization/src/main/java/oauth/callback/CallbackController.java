package oauth.callback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author payno
 * @date 2020/5/19 17:03
 * @description
 */
@RestController
public class CallbackController {
    @RequestMapping("/callback")
    public void callback(HttpServletRequest request){
        String code = request.getParameter("code");
    }
}
