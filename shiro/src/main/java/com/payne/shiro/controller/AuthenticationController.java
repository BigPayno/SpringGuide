package com.payne.shiro.controller;

import com.payne.shiro.controller.dto.LoginDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author payno
 * @date 2019/12/3 16:02
 * @description
 */
@RestController
@RequestMapping
public class AuthenticationController {
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SecurityUtils.getSubject().login(new UsernamePasswordToken("user","user"));
        /**
         * 利用Shiro的SavedRequest方法进行微妙的重定向
         */
        SavedRequest savedRequest=WebUtils.getAndClearSavedRequest(request);
        response.sendRedirect(savedRequest.getRequestURI());
        return "login";
    }
    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto){
         /**
         * Subject信息保存在ThreadLocal中，通过ThreadContext得到
         * 默认的WebSubject保存了HttpRequest，HttpResponse信息
         * Subject保存在Shiro的Session中，HttpServletSession是Web默认实现
         */
        System.out.println(loginDto);
        Subject subject= SecurityUtils.getSubject();
        try {
            /**
             * 绑定token到Subject中，在SecurityManager中login
             * SecurityManager会最终调用Realm的
             *      AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
             * 得到登陆结果信息
             */
            subject.login(loginDto.toToken());
        } catch (UnknownAccountException e) {
        } catch (IncorrectCredentialsException e) {
        } catch (LockedAccountException e) {
        } catch (AuthenticationException e) {
        }

    }

    /**
     * 进行过登陆，请求cookie中带有sessionId的可以直接进入
     * 没进行过登陆，将进入login
     */
    @GetMapping("/redirect")
    public void redirect(){
        System.out.println("redirect ok!");
    }
}
