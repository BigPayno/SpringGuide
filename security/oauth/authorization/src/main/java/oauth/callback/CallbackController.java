package oauth.callback;

import com.google.common.collect.Lists;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Map;

/**
 * @author payno
 * @date 2020/5/19 17:03
 * @description
 */
@RestController
public class CallbackController {
    @RequestMapping("/callback")
    public Object callback(HttpServletRequest request){
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.setInterceptors(Lists.newArrayList((ClientHttpRequestInterceptor) (httpRequest, bytes, execution)->{
            httpRequest.getHeaders().set("Authorization",String.format("Basic %s", Base64.getEncoder().encodeToString("app1:112233".getBytes())));
            httpRequest.getHeaders().set("Cookie", request.getHeader("Cookie"));
            return execution.execute(httpRequest,bytes);
        }));
        Object token = restTemplate.postForObject(
                UriComponentsBuilder.fromUriString("http://localhost:8080/oauth/token")
                        .queryParam("code",request.getParameter("code"))
                        .queryParam("grant_type","authorization_code")
                        .queryParam("scope","read_contacts").toUriString(),
                null, Object.class);
        return token;
    }
}
