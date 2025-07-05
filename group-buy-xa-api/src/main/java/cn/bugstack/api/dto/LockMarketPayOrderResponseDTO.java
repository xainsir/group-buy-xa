package cn.bugstack.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * LockMarketPayOrderResponseDTO
 * @description 锁营销支付订单应答体
 * @author xainsir
 * @date 2025/7/5 16:02
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LockMarketPayOrderResponseDTO {
    private String orderId;             /** 预购订单ID */
    private BigDecimal deductionPrice;  /** 折扣金额 */
    private Integer tradeOrderStatus;   /** 交易订单状态 */

}