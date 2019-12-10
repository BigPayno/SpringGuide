package com.example.session.mvc;

import com.example.session.tomcat.RequestHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Session;
import org.apache.catalina.connector.RequestFacade;

import javax.servlet.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author payno
 * @date 2019/12/10 09:38
 * @description
 *      HttpServletRequest <|- Request<-<>RequestFacade
 *      HttpSession <|-Session<-<>StandardSessionFacade
 */
@Slf4j
public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("SessionFilter初始化成功！");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(String.format("%nremoteHost:%s",servletRequest.getRemoteHost()));
        stringBuilder.append(String.format("%nrequestClass:%s",servletRequest.getClass()));
        /**
         * Tomcat会包装成RequestFacade implements HttpServletRequest
         * 真实调用的是Request
         */
        if(servletRequest instanceof RequestFacade){
            Session session= RequestHelper.getCurSession((RequestFacade) servletRequest);
            if(session==null){
                stringBuilder.append(String.format("%nthis request still not create session"));
                ((RequestFacade) servletRequest).getSession();
                stringBuilder.append(String.format("%ncreate session by getSession[Type:%s]",((RequestFacade) servletRequest).getSession().getClass()));
                stringBuilder.append("wrap the request to SessionRepositoryWrapper");
            }
        }
        log.info(stringBuilder.toString());
        SessionRepositoryWrapper sessionRepositoryWrapper=new SessionRepositoryWrapper((RequestFacade)servletRequest);
        HttpSession session1=sessionRepositoryWrapper.getSession();
        session1.setAttribute("abc","def");
        filterChain.doFilter(sessionRepositoryWrapper,servletResponse);
    }
}
