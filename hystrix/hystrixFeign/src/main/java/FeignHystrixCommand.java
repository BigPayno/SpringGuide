/**
 * @author payno
 * @date 2020/5/29 10:47
 * @description
 */
public class FeignHystrixCommand {
    /**
     *  Feign集成Hystrix的关键在于 JDK的代理机制
     *      类拦截接口 InvocationHandler
     *      HystrixInvocationHandler implements InvocationHandler
     *      <=> HystrixInvocationHandler extends HystrixCommand<R>
     */
}
