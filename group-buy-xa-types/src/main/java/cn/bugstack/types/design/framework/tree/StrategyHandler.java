package cn.bugstack.types.design.framework.tree;

/**
 * StrategyHandler
 * @description 策略受理器
 * @author xainsir
 * @date 2025/6/27 16:41
 */
public interface StrategyHandler<T, D, R> {

    // 默认的策略处理器，当没有匹配的策略处理器时，使用默认的策略处理器。
    StrategyHandler DEFAULT = (T, D) -> null;

    // 受理执行的业务流程。每个业务流程执行时，如果有数据是从前面节点到后面节点要使用的，那么可以填充到 dynamicContext 上下文中。
    R apply(T requestParameter, D dynamicContext) throws Exception;
}