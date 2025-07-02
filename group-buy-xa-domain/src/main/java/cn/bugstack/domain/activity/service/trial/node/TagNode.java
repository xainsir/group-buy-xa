package cn.bugstack.domain.activity.service.trial.node;

import cn.bugstack.domain.activity.model.entity.MarketProductEntity;
import cn.bugstack.domain.activity.model.entity.TrialBalanceEntity;
import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.model.valobj.TagScopeEnumVo;
import cn.bugstack.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import cn.bugstack.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import cn.bugstack.types.design.framework.tree.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * TagNode
 * @description 人群标签筛选节点
 * @author xainsir
 * @date 2025/7/2 13:34
 */
@Service
@Slf4j
public class TagNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {
    @Resource
    private ErrorNode errorNode;
    @Resource
    private EndNode endNode;
    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        // 1. 获取拼团活动配置
        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = dynamicContext.getGroupBuyActivityDiscountVO();
        if(Objects.isNull(groupBuyActivityDiscountVO)){
            router(requestParameter, dynamicContext);
        }
        String tagId = groupBuyActivityDiscountVO.getTagId();
        boolean visible = groupBuyActivityDiscountVO.isVisible();
        boolean enable = groupBuyActivityDiscountVO.isEnable();

        // 2. 人群标签配置为空，则走默认值

        if(StringUtils.isBlank(tagId)){
            dynamicContext.setVisible(TagScopeEnumVo.ENABLE.getAllow());
            dynamicContext.setEnable(TagScopeEnumVo.VISIBLE.getAllow());
            return router(requestParameter, dynamicContext); // 并且路由到下一节点
        }
        // 3. 是否在人群范围内；visible、enable 如果值为 ture 则表示没有配置拼团限制，那么就直接保证为 true 即可
        boolean within= activityRepository.isTagCrowdRange(groupBuyActivityDiscountVO.getTagId(),requestParameter.getUserId());
        dynamicContext.setVisible(within || visible);
        dynamicContext.setEnable(within || enable);

        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        if(Objects.isNull(dynamicContext.getGroupBuyActivityDiscountVO())){
            return errorNode;
        }
        // 去往最终节点
        return endNode;
    }
}
 