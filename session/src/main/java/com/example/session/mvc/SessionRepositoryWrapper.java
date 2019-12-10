package com.example.session.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * @author payno
 * @date 2019/12/10 10:23
 * @description
 */
public class SessionRepositoryWrapper extends HttpServletRequestWrapper {
    public static ThreadLocal<EasySession> sessionThreadLocal=new ThreadLocal<>();
    public SessionRepositoryWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public HttpSession getSession(boolean create) {
        EasySession session=sessionThreadLocal.get();
        if(session==null&&create){
            session=new EasySession();
            sessionThreadLocal.set(session);
        }
        return session;
    }

    @Override
    public HttpSession getSession() {
        EasySession session=sessionThreadLocal.get();
        if(session==null){
            session=new EasySession();
            sessionThreadLocal.set(session);
        }
        return session;
    }
}
