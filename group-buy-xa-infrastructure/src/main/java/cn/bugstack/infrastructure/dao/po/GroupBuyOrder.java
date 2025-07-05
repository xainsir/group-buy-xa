package cn.bugstack.infrastructure.dao.po;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;
/**
 * GroupBuyOrder
 * @description 用户拼单
 * @author xainsir
 * @date 2025/7/5 1:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyOrder {
    private Long id;                /** 自增ID */
    private String teamId;          /** 拼单组队ID */
    private Long activityId;        /** 活动ID */
    private String source;          /** 订单来源 */
    private String channel;         /** 订单渠道 */
    private BigDecimal originalPrice; /** 原价 */
    private BigDecimal deductionPrice; /** 优惠价 */
    private BigDecimal payPrice;        /** 实付价 */
    private Integer targetCount;    /** 目标数量 */
    private Integer completeCount; /** 完成数量 */
    private Integer lockCount;      /** 锁单数量 */
    private Integer status;         /** 状态(0-拼单中、1-完成、2-失败)*/
    private Date createTime;        /** 创建时间 */
    private Date updateTime;        /** 更新时间 */
}