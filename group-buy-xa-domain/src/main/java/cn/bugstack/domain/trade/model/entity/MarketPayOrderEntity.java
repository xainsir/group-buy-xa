package cn.bugstack.domain.trade.model.entity;
import cn.bugstack.domain.trade.model.valobj.TradeOrderStatusEnumVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * MarketPayOrderEntity
 * @description 营销支付订单实体
 * @author xainsir
 * @date 2025/7/5 2:38
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketPayOrderEntity {

    private String orderId;                                 /** 预购订单ID */
    private BigDecimal deductionPrice;                      /** 折扣金额 */
    private TradeOrderStatusEnumVO tradeOrderStatusEnumVO;  /** 交易订单状态枚举 */

}
 