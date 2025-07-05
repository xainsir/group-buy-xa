package cn.bugstack.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LockMarketPayOrderRequestDTO
 * @description 锁营销支付订单请求体
 * @author xainsir
 * @date 2025/7/5 16:02
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LockMarketPayOrderRequestDTO {

    private String userId;// 用户ID
    private String teamId;// 拼单组队ID - 可为空，为空则创建新组队ID
    private Long activityId;// 活动ID
    private String goodsId; // 商品ID
    private String source; //来源
    private String channel; // 渠道
    private String outTradeNo; // 外部订单号

}