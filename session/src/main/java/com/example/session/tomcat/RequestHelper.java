package com.example.session.tomcat;

import org.apache.catalina.Session;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author payno
 * @date 2019/12/10 09:57
 * @description
 *     因为Request.getSession总是有Session返回，如果想真实拿到Session必须通过反射
 */
public final class RequestHelper {
    private static Request getCurRequest(RequestFacade requestFacade) throws IllegalAccessException{
        Field field=ReflectionUtils.findField(RequestFacade.class,"request",Request.class);
        field.setAccessible(true);
        return (Request) field.get(requestFacade);
    }
    public static Session getCurSession(RequestFacade requestFacade){
        try{
            Request request=getCurRequest(requestFacade);
            Field field=ReflectionUtils.findField(Request.class,"session");
            field.setAccessible(true);
            return (Session)field.get(request);
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        RequestFacade requestFacade=new RequestFacade(new Request(new Connector()));
        Session session=getCurSession(requestFacade);
        System.out.println(session==null);
    }
}
