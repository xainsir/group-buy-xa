package cn.bugstack.domain.activity.service;

import cn.bugstack.domain.activity.model.entity.MarketProductEntity;
import cn.bugstack.domain.activity.model.entity.TrialBalanceEntity;

/**
 * IIndexGroupBuyMarketService
 * @description 首页营销服务接口
 * @author xainsir
 * @date 2025/6/27 16:50
 */

public interface IIndexGroupBuyMarketService {
    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception;
}
 