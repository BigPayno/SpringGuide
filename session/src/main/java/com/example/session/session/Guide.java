package com.example.session.session;

/**
 * @author payno
 * @date 2019/12/10 14:38
 * @description
 */
public class Guide {
    /**
     * SessionRepositoryFilter  -|>  OncePerRequestFilter-|>Filter
     *                                   使该Filter只执行一次，通过在Request添加参数作为信号量
     *                          <>-> httpSessionIdResolver[CookieHttpSessionIdResolver]
     *                                   保存sessionId的策略，主要有Cookie和Token两种方式
     *                          <>-> SessionRepository
     *                                   在request中保存sessionRepository来以供后续使用
     *                          $SessionRepositoryResponseWrapper  -|>  HttpServletResponseWrapper
     *                          $SessionRepositoryRequestWrapper   -|>  HttpServletRequestWrapper
     *                                  内部对Request和Response进行装饰的包装类,里面通过委托
     *                                  sessionRepository进行session的提交，获取等操作
     *
     *
     */
    public static void main(String[] args) {

    }
}
