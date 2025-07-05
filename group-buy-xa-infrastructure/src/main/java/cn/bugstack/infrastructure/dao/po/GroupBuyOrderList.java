package cn.bugstack.infrastructure.dao.po;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
/**
 * GroupBuyOrder
 * @description 用户拼单明细
 * @author xainsir
 * @date 2025/7/5 1:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyOrderList {
    private Long id;                /** 自增ID */
    private String userId;            /** 用户ID */
    private String teamId;          /** 拼单组队ID */
    private Long activityId;        /** 活动ID */
    private String orderId;         /** 订单ID */
    private String goodsId;         /** 商品ID */
    private String outTradeNo;      /** 外部交易单号，外部唯一幂等 */
    private String source;          /** 订单来源 */
    private String channel;         /** 订单渠道 */
    private BigDecimal originalPrice;  /** 原价 */
    private BigDecimal deductionPrice; /** 优惠价 */
    private Integer status;         /** 状态(0初始锁定、1消费完成)*/
    private Date startTime;         /** 活动开始时间 */
    private Date endTime;           /** 活动结束时间 */
    private Date createTime;        /** 创建时间 */
    private Date updateTime;        /** 更新时间 */
}