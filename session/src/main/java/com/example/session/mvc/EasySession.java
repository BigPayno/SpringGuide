package com.example.session.mvc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @author payno
 * @date 2019/12/10 11:09
 * @description
 */
public class EasySession implements HttpSession {
    private HashMap<String,Object> map=new HashMap<>();
    @Override
    public long getCreationTime() {
        return System.currentTimeMillis();
    }

    @Override
    public String getId() {
        return String.valueOf(Thread.currentThread().getId());
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int i) {

    }

    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    @Override
    public Object getAttribute(String s) {
        return map.get(s);
    }

    @Override
    public Object getValue(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String[] getValueNames() {
        return new String[0];
    }

    @Override
    public void setAttribute(String s, Object o) {
        map.put(s,o);
    }

    @Override
    public void putValue(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {
        map.remove(s);
    }

    @Override
    public void removeValue(String s) {

    }

    @Override
    public void invalidate() {

    }

    @Override
    public boolean isNew() {
        return false;
    }
}
