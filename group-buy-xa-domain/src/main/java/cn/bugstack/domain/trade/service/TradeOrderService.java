package cn.bugstack.domain.trade.service;

import cn.bugstack.domain.trade.adapter.repository.ITradeRepository;
import cn.bugstack.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import cn.bugstack.domain.trade.model.entity.MarketPayOrderEntity;
import cn.bugstack.domain.trade.model.entity.PayActivityEntity;
import cn.bugstack.domain.trade.model.entity.PayDiscountEntity;
import cn.bugstack.domain.trade.model.entity.UserEntity;
import cn.bugstack.domain.trade.model.valobj.GroupBuyProgressVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TradeOrderService
 * @description 交易订单服务实现类
 * @author xainsir
 * @date 2025/7/5 10:55
 */
@Slf4j
@Service
public class TradeOrderService implements ITradeOrderService{
    @Resource
    private ITradeRepository tradeRepository;

    @Override
    public MarketPayOrderEntity queryNoPayMarketOrderByOutTradeNo(String userId, String outTradeNo) {
        return tradeRepository.queryNoPayMarketOrderByOutTradeNo(userId, outTradeNo);
    }

    @Override
    public GroupBuyProgressVO queryGroupBuyProgress(String teamId) {
        return tradeRepository.queryGroupBuyProgress(teamId);
    }

    @Override
    public MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity) {
        GroupBuyOrderAggregate groupBuyOrderAggregate = GroupBuyOrderAggregate.builder()
                .payActivityEntity(payActivityEntity)
                .payDiscountEntity(payDiscountEntity)
                .userEntity(userEntity)
                .build();
        return tradeRepository.lockMarketPayOrder(groupBuyOrderAggregate);
    }
}
 