package cn.bugstack.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * PayActivityEntity
 * @description 支付活动实体
 * @author xainsir
 * @date 2025/7/5 2:32
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayActivityEntity {

    private String teamId;         /** 拼单组队ID */
    private Long activityId;       /** 活动ID */
    private String activityName;   /** 活动名称 */
    private Date startTime;        /** 活动开始时间 */
    private Date endTime;          /** 活动结束时间 */
    private Integer targetCount;   /** 活动目标人数 */

}