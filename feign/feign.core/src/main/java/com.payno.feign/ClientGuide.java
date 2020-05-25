package com.payno.feign;

/**
 * @author payno
 * @date 2020/5/25 11:42
 * @description
 */
public class ClientGuide {
    /**
     *  4.1.1 OkHttp
     *      使用 Feign OkHttp 直接将 Feign 请求交由 OkHttp 处理。
     *      相比 Feign 使用的默认的 HTTP 客户端，OkHttp 可以提供更好的性能和特性。
     *      Feign 使用 OkHttp 只需要将 OkHttp 裤加入到 classpath 中，然后配置 Feign 使用 OkHttpClient。
     *
     *
     * 4.1.2 Ribbon
     *      Feign Ribbon 为 Feign Client重写 URL 解析，同时提供了智能路由和弹性能力。
     *      集成 Ribbon 时，需要将 url 改为 Ribbon 的客户端名称，例如说 "myAppProd"
     *
     * 4.1.3 Java 11 Http2
     *      Feign Java 11 直接将 Feign HTTP 请求交由 Java11 New HTTP/2 Client 处理以实现 HTTP/2。
     *      若为 Feign 使用 New HTTP/2 客户端需要 JDK11，然后配置 Feign 使用 Http2Client。
     */
}
