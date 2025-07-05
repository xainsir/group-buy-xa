package cn.bugstack.domain.trade.adapter.repository;

import cn.bugstack.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import cn.bugstack.domain.trade.model.entity.MarketPayOrderEntity;
import cn.bugstack.domain.trade.model.valobj.GroupBuyProgressVO;

/**
 * ITradeRepository
 * @description
 * @author xainsir
 * @date 2025/7/5 10:54
 */
public interface ITradeRepository {
    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    MarketPayOrderEntity queryNoPayMarketOrderByOutTradeNo(String userId, String outTradeNo);

    MarketPayOrderEntity lockMarketPayOrder(GroupBuyOrderAggregate groupBuyOrderAggregate);
}
