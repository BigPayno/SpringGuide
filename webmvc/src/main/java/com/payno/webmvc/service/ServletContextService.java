package com.payno.webmvc.service;

import com.google.common.base.Splitter;
import org.apache.tomcat.util.threads.TaskThread;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Enumeration;
import java.util.List;

/**
 * @author payno
 * @date 2019/11/28 16:46
 * @description
 *      1. HttpServletBean 进行初始化工作
 *      2. FrameworkServlet 初始化 WebApplicationContext,并提供service方法预处理请求
 *      3. DispatcherServlet 具体分发处理.
 * 那么就可以在FrameworkServlet查看到该类重写了service(),doGet(),doPost()…等方法,
 *      这些实现里面都有一个预处理方法processRequest(request, response);,
 *      所以定位到了我们要找的位置
 * 查看processRequest(request, response);的实现,具体可以分为三步:
 *      1. 获取上一个请求的参数
 *      2. 重新建立新的参数
 *      3. 设置到XXContextHolder
 *      4. 父类的service()处理请求
 *      5. 恢复request
 *      6. 发布事件
 */
@Component
public class ServletContextService {
    public void display(){
        /**
         * 查看当前线程
         */
        TaskThread taskThread=(TaskThread)Thread.currentThread();
        ServletRequestAttributes servletRequestAttributes=
                (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        /**
         * 通过ThreadLocal保存请求与回复信息
         */
        HttpServletRequest request=servletRequestAttributes.getRequest();
        HttpServletResponse response=servletRequestAttributes.getResponse();
        System.out.println(request.getRequestURI());
        Enumeration<String> headerNames=request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName=headerNames.nextElement();
            System.out.printf("%s -> %s%n",headerName,request.getHeader(headerName));
            if(headerName.equals("authorization")){
                List<String> list=Splitter.on(" ").splitToList(request.getHeader(headerName));
                byte[] bytes=Base64.getDecoder().decode(list.get(list.size()-1));
                System.out.println("解密后:");
                System.out.println(new String(bytes));
            }
        }
    }
}
