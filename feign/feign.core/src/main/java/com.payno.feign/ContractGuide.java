package com.payno.feign;

/**
 * @author payno
 * @date 2020/5/25 11:30
 * @description
 */
public class ContractGuide {
    /**
     *  Contract.Default 默认契约 Feign自身的契约
     *
     *  ① @RequestLine 注解，添加在方法上，设置请求方法和请求地址，按照 请求方法 请求地址 格式，例如说 GET /user/get。同时，可以通过 {param} 表达式声明占位参数，搭配 @Param 注解一起使用。
     *
     * ② @Param 注解，添加在方法参数上，设置占位参数。不过要注意，@Param 是必传参数，因此传入 null 会报错。
     *
     * ③ @QueryMap 注解，请求 Query 参数集，无需像 @Param 在 @RequestLine 定义 {param} 表达式进行占位。
     *
     * 这里，我们使用在 #list(@QueryMap Map<String, Object> queryMap) 方法上，解决 #list(@Param("name") String name, @Param("gender") Integer gender) 方法在使用 @Param 注解时传递 null 会报错的问题。
     *
     * ④ @Headers 注解，添加在方法上，设置请求头。
     */
}
