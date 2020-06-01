package core;

/**
 * @author payno
 * @date 2020/6/1 08:54
 * @description
 */
public class ContextUtilGuide {
    /**
     * ContextUtil
     * 标识进入调用链入口（上下文）：
     *
     * 以下静态方法用于标识调用链路入口，用于区分不同的调用链路：
     *
     *     public static Context enter(String contextName)
     *     public static Context enter(String contextName, String origin)
     *
     * 其中 contextName 代表调用链路入口名称（上下文名称），origin 代表调用来源名称。默认调用来源为空。返回值类型为 Context，即生成的调用链路上下文对象。
     *
     * 流控规则中若选择“流控方式”为“链路”方式，则入口资源名即为上面的 contextName。
     *
     * 注意：
     *
     *     ContextUtil.enter(xxx) 方法仅在调用链路入口处生效，即仅在当前线程的初次调用生效，后面再调用不会覆盖当前线程的调用链路，直到 exit。Context 存于 ThreadLocal 中，因此切换线程时可能会丢掉，如果需要跨线程使用可以结合 runOnContext 方法使用。
     *     origin 数量不要太多，否则内存占用会比较大。
     *
     * 退出调用链（清空上下文）：
     *
     *     public static void exit()：该方法用于退出调用链，清理当前线程的上下文。
     *
     * 获取当前线程的调用链上下文：
     *
     *     public static Context getContext()：获取当前线程的调用链路上下文对象。
     *
     * 在某个调用链上下文中执行代码：
     *
     *     public static void runOnContext(Context context, Runnable f)：常用于异步调用链路中 context 的变换。
     */
}
