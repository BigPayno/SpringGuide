package api.event;

/**
 * @author payno
 * @date 2020/5/25 11:02
 * @description
 */
public class EventGuide {
    /**
     *
     *  监听器注入方式
     *      1.类+implements ApplicationListener<EnvironmentChangeEvent>
     *      2.方法+@EventListener
     *
     *  ① 如果想要多个监听器按照指定顺序执行，可以通过实现 Ordered 接口，指定其顺序。
     *
     * ② 如果想要监听多种 ApplicationContext 事件，可以实现 SmartApplicationListener 接口，具体示例可以看看 SourceFilteringListener 类。
     *
     * ③ @TransactionalEventListener 注解，可以声明在当前事务“结束”时，执行相应的监听逻辑。
     *
     * ④ 可以通过实现 ApplicationEventMulticaster 接口，定义自定义的事件广播器，可以往里面添加和移除监听器，并发布事件给注册在其中的监听器。使用比较少，基本可以忽略。
     */
}
