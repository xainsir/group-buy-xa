package cn.bugstack.domain.activity.service.trial.node;
import cn.bugstack.domain.activity.model.entity.MarketProductEntity;
import cn.bugstack.domain.activity.model.entity.TrialBalanceEntity;
import cn.bugstack.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import cn.bugstack.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import cn.bugstack.types.design.framework.tree.StrategyHandler;
import cn.bugstack.types.enums.ResponseCode;
import cn.bugstack.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SwitchRoot
 * @description 开关节点
 * @author xainsir
 * @date 2025/6/28 18:37
 */
@Service
@Slf4j
public class SwitchRoot extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {
    @Resource
    private MarketNode marketNode;
    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {

        String userId = requestParameter.getUserId();
        // 降级开关判断
        if(activityRepository.downgradeSwitch()){
            throw new AppException(ResponseCode.E0003.getCode(),ResponseCode.E0003.getInfo());
        }
        // 切量开关判断
        if(activityRepository.cutRange(userId)){
            throw new AppException(ResponseCode.E0004.getCode(),ResponseCode.E0004.getInfo());
        }


        return router(requestParameter, dynamicContext);// 做下路由
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return marketNode;// 路由到下一个节点：营销
    }
}
 