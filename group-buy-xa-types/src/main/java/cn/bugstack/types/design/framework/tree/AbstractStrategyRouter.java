package cn.bugstack.types.design.framework.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * AbstractStrategyRouter
 * @description 策略路由器
 * @author xainsir
 * @date 2025/6/27 16:38
 */
public abstract class AbstractStrategyRouter<T, D, R> implements StrategyMapper<T, D, R>, StrategyHandler<T, D, R> {
    @Getter
    @Setter
    protected StrategyHandler<T, D, R> defaultStrategyHandler = StrategyHandler.DEFAULT;// 默认处理策略

    public R router(T requestParameter, D dynamicContext) throws Exception {
        StrategyHandler<T, D, R> strategyHandler = get(requestParameter, dynamicContext);  //通过调用策略映射器get方法，控制节点流程的走向。
        if(null != strategyHandler) return strategyHandler.apply(requestParameter, dynamicContext);
        return defaultStrategyHandler.apply(requestParameter, dynamicContext);
    }
}
 