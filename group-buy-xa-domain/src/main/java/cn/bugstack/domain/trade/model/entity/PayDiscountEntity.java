package cn.bugstack.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * PayDiscountEntity
 * @description 支付优惠实体
 * @author xainsir
 * @date 2025/7/5 2:32
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayDiscountEntity { // 对商品做的优惠

    private String source;             /** 来源 */
    private String channel;            /** 渠道 */
    private String goodsId;            /** 商品ID */
    private String goodsName;          /** 商品名称 */
    private BigDecimal originalPrice;  /** 原价 */
    private BigDecimal deductionPrice; /** 优惠金额 */
    private String outTradeNo;         /** 外部订单号，外部调用唯一幂等 */
}