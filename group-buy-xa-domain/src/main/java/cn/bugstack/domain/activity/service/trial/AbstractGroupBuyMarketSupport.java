package cn.bugstack.domain.activity.service.trial;

import cn.bugstack.domain.activity.adapter.repository.IActivityRepository;
import cn.bugstack.domain.activity.model.entity.MarketProductEntity;
import cn.bugstack.domain.activity.model.entity.TrialBalanceEntity;
import cn.bugstack.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import cn.bugstack.types.design.framework.tree.AbstractMultiThreadStrategyRouter;
import cn.bugstack.types.design.framework.tree.AbstractStrategyRouter;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * AbstractGroupBuyMarketSupport
 * @description 抽象拼团营销支撑类
 * @author xainsir
 * @date 2025/6/27 16:49
 */
public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<cn.bugstack.domain.activity.model.entity.MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, cn.bugstack.domain.activity.model.entity.TrialBalanceEntity> {

    protected long timeout = 500;
    @Resource
    protected IActivityRepository activityRepository;

    @Override
    public void multiThread(cn.bugstack.domain.activity.model.entity.MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        // 缺省方法 可以不需要后续继承类实现这个方法了
    }
}
 