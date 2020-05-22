package com.payno.spring.security.base.filter;

/**
 * @author payno
 * @date 2020/5/22 09:55
 * @description
 */
public class ChannelProcessingFilterGuide {
    /**
     *  ChannelProcessingFilter extends GenericFilterBean
     *  doFilter()方法主要做了两步:
     *      第一步, 找出当前请求所需要的协议. attr的值可以是ANY_CHANNEL(http和https都可以),
     *      REQUIRES_SECURE_CHANNEL(必须是https协议), REQUIRES_INSECURE_CHANNEL(必须是http协议).
     *      第二步, 判断当前请求是否与协议相符. 若不相符, 则修改协议并自动跳转. 若相符,
     *      则跳到下一个过滤器.
     *
     *  ChannelDecisionManager具体处理逻辑
     *  FilterInvocationSecurityMetadataSource 资源管理
     *  FilterInvocation 类似Context
     */
}
