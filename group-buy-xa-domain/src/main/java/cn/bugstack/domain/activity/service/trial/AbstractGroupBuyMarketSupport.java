package cn.bugstack.domain.activity.service.trial;

import cn.bugstack.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import cn.bugstack.types.design.framework.tree.AbstractStrategyRouter;
/**
 * AbstractGroupBuyMarketSupport
 * @description 抽象拼团营销支撑类
 * @author xainsir
 * @date 2025/6/27 16:49
 */
public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractStrategyRouter<cn.bugstack.domain.activity.model.entity.MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, cn.bugstack.domain.activity.model.entity.TrialBalanceEntity> {

}
 