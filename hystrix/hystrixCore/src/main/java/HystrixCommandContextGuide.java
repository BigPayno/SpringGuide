import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * @author payno
 * @date 2020/5/29 14:29
 * @description
 */
public class HystrixCommandContextGuide {
    public static class CachedCommand extends HystrixCommand<String>{

        private String observable;

        protected CachedCommand(String observable) {
            super(Setter
                    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("Cached"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("CachedCommand"))
            );
            this.observable = observable;
        }

        public static CachedCommand of(String observable){
            return new CachedCommand(observable);
        }

        @Override
        protected String getCacheKey() {
            return observable;
        }

        public static void flushCache(String key) {
            HystrixRequestCache.getInstance(
                    HystrixCommandKey.Factory.asKey("CachedCommand"),
                    HystrixConcurrencyStrategyDefault.getInstance()
            ).clear(key);
        }

        @Override
        protected String run() throws Exception {
            Threads.PrintStream.Err.println(observable);
            return observable;
        }
    }

    public static void main(String[] args) {
        String payno = "payno";
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        CachedCommand cachedCommand = CachedCommand.of(payno);
        cachedCommand.execute();
        System.out.println(cachedCommand.isResponseFromCache());

        CachedCommand cachedCommand2 = CachedCommand.of(payno);
        cachedCommand2.execute();
        System.out.println(cachedCommand2.isResponseFromCache());

        //清除缓存
        CachedCommand.flushCache(payno);

        CachedCommand cachedCommand3 = CachedCommand.of(payno);
        cachedCommand3.execute();
        System.out.println(cachedCommand3.isResponseFromCache());

        context.shutdown();
    }
}
