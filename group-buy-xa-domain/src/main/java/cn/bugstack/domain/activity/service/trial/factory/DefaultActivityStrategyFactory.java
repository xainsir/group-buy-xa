package cn.bugstack.domain.activity.service.trial.factory;

import cn.bugstack.domain.activity.model.entity.MarketProductEntity;
import cn.bugstack.domain.activity.model.entity.TrialBalanceEntity;
import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.model.valobj.SkuVO;
import cn.bugstack.domain.activity.service.trial.node.RootNode;
import cn.bugstack.types.design.framework.tree.StrategyHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * DefaultActivityStrategyFactory
 * @description 活动策略工厂
 * @author xainsir
 * @date 2025/6/27 16:52
 */
@Service
public class DefaultActivityStrategyFactory {
    private final RootNode rootNode;// 逻辑流转时的根节点

    public DefaultActivityStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public StrategyHandler<MarketProductEntity, DynamicContext, TrialBalanceEntity> strategyHandler() {
        return rootNode;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {// 工厂动态上下文
        private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;// 拼团活动折扣信息
        private SkuVO skuVO; // 商品信息
        private BigDecimal deductionPrice;// 优惠金额
        private Boolean visible; // 是否活动限制可见
        private Boolean enable;  // 是否活动限制参与

    }
}