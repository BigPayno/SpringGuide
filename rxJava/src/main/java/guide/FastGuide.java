package guide;

import com.sun.jmx.snmp.tasks.ThreadService;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

import javax.annotation.concurrent.ThreadSafe;

/**
 * @author payno
 * @date 2020/5/29 09:37
 * @description
 */
public class FastGuide {
    public static Observable<String> observable(){
        Observable novel=Observable.create(emitter->{
            emitter.onNext("unit1");
            emitter.onNext("unit2");
            emitter.onNext("unit3");
            emitter.onComplete();
        });
        return novel;
    }

    public static Observer<String> observer(){
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(d);
            }

            @Override
            public void onNext(String s) {
                Threads.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" read "+s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("something error");
            }

            @Override
            public void onComplete() {
                System.out.println(Thread.currentThread().getName()+" finish reading!");
            }
        };
    }

    @Test
    public void demo1(){
        observable().subscribe(observer());
    }
}
