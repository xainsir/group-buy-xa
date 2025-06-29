package cn.bugstack.types.design.framework.tree;

import cn.bugstack.types.design.framework.tree.StrategyMapper;
import cn.bugstack.types.design.framework.tree.StrategyHandler;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * AbstractMultiThreadStrategyRouter
 * @description 异步资源加载策略
 * @author xainsir
 * @date 2025/6/28 20:12
 */
public abstract class AbstractMultiThreadStrategyRouter<T,D,R> implements StrategyMapper<T,D,R>, StrategyHandler<T,D,R>{


    protected StrategyHandler<T,D,R>defaultStrategyHandler = StrategyHandler.DEFAULT;
    public R router(T requestParameter, D dynamicContext) throws Exception {
        StrategyHandler<T,D,R> strategyHandler = get(requestParameter, dynamicContext);
        if (null == strategyHandler) strategyHandler = defaultStrategyHandler;
        return strategyHandler.apply(requestParameter, dynamicContext);
    }
    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {
        // 异步加载数据
        multiThread(requestParameter, dynamicContext);
        // 业务流程受理
        return doApply(requestParameter, dynamicContext);
    }
    // 异步加载数据
    public abstract void multiThread(T requestParameter, D dynamicContext) throws ExecutionException,InterruptedException, TimeoutException;

    // 业务流程受理
    public abstract R doApply(T requestParameter, D dynamicContext) throws Exception;
}
 