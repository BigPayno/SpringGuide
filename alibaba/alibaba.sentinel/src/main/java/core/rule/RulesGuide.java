package core.rule;

/**
 * @author payno
 * @date 2020/5/31 15:59
 * @description
 */
public class RulesGuide {
    /**
     * FlowRule
     *  resource    资源名，资源名是限流规则的作用对象
     *  count       限流阈值
     *  grade       限流阈值类型，QPS 或线程数模式
     *  limitApp    流控针对的调用来源 default，代表不区分调用来源
     *  strategy    判断的根据是资源自身，还是根据其它关联资源 (refResource)，还是根据链路入口 根据资源本身
     *  controlBehavior 流控效果（直接拒绝 / 排队等待 / 慢启动模式） 直接拒绝
     *
     * DegradeRule
     *  resource 	            资源名，即限流规则的作用对象
     *  count 	                阈值
     *  grade 	                熔断策略，支持秒级 RT/秒级异常比例/分钟级异常数 	秒级平均 RT
     *  timeWindow 	            降级的时间，单位为 s
     *  rtSlowRequestAmount 	RT 模式下 1 秒内连续多少个请求的平均 RT 超出阈值方可触发熔断（1.7.0 引入） 	5
     *  minRequestAmount 	    异常熔断的触发最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断（1.7.0 引入） 	5
     *
     * SystemRule
     *  highestSystemLoad 	    load1 触发值，用于触发自适应控制阶段 	-1 (不生效)
     *  avgRt 	                所有入口流量的平均响应时间 	            -1 (不生效)
     *  maxThread 	            入口流量的最大并发数 	                -1 (不生效)
     *  qps 	                所有入口资源的 QPS 	                    -1 (不生效)
     *  highestCpuUsage 	    当前系统的 CPU 使用率（0.0-1.0） 	    -1 (不生效)
     *
     * AuthorityRule
     *  resource            资源名，即限流规则的作用对象
     *  limitApp            对应的黑名单/白名单，不同 origin 用 , 分隔，如 appA,appB
     *  strategy            限制模式，AUTHORITY_WHITE 为白名单模式，AUTHORITY_BLACK 为黑名单模式，默认为白名单模式
     */
}
