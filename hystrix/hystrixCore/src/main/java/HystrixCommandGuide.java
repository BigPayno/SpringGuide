import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Objects;
import java.util.concurrent.Future;

/**
 * @author payno
 * @date 2020/5/29 09:04
 * @description
 */
public class HystrixCommandGuide {

    static class Command extends HystrixCommand<String>{

        public String observable;

        protected Command(String observable) {
            super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
            this.observable = observable;
        }

        public static Command of(String observable){
            return new Command(observable);
        }

        @Override
        protected String run() throws Exception {
            Objects.requireNonNull(observable);
            Threads.PrintStream.Out.println(observable);
            return observable;
        }

        @Override
        protected String getFallback() {
            Threads.PrintStream.Err.println(observable);
            return "something error";
        }
    }

    public static void main(String[] args) {
        Command.of("hello").execute();
        Command.of(null).execute();
        Command.of("hello").queue();
        Command.of("payno").observe()
                .doOnNext(Threads.PrintStream.Err::println)
                .doOnNext(Threads.PrintStream.Out::println)
                .subscribe();
    }
}
