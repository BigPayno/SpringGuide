package core;

import com.alibaba.csp.sentinel.slotchain.ProcessorSlot;

/**
 * @author payno
 * @date 2020/5/31 14:04
 * @description
 */
public class ProcessorSlotSPI {
    /**
     * NodeSelectorSlot 负责收集资源的路径，并将这些资源的调用路径，以树状结构存储起来，用于根据调用路径来限流降级；
     *
     * ClusterBuilderSlot 则用于存储资源的统计信息以及调用者信息，例如该资源的 RT, QPS, thread count 等等，这些信息将用作为多维度限流，降级的依据；
     *
     * StatisticsSlot 则用于记录，统计不同维度的 runtime 信息；
     *
     * SystemSlot 则通过系统的状态，例如 load1 等，来控制总的入口流量；
     *
     * AuthoritySlot 则根据黑白名单，来做黑白名单控制；
     *
     * FlowSlot 则用于根据预设的限流规则，以及前面 slot 统计的状态，来进行限流；
     *
     * DegradeSlot 则通过统计信息，以及预设的规则，来做熔断降级；
     */
    public static void main(String[] args) {
        
    }
}
