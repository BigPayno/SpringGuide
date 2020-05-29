import feign.RequestLine;
import feign.hystrix.FallbackFactory;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;

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

    public interface EasyClient {
        @RequestLine("POST /error")
        String get();
    }

    public static class FallbackClient implements EasyClient{
        public String get() {
            return "default";
        }
    }

    /**
     * @HystrixFeign.build()是集成的关键
     *     契约的装饰器 HystrixDelegatingContract
     *     InvocationHandlerFactory代理
     */

    public static void main(String[] args) {
        EasyClient hystrixFeignClient = HystrixFeign.builder()
                .setterFactory(new SetterFactory.Default())
                .target(EasyClient.class, "http://localhost:8080", new FallbackFactory<EasyClient>() {
                    public EasyClient create(Throwable cause) {
                        return new FallbackClient();
                    }
                });
        System.out.println(hystrixFeignClient.get());
    }
}
