package cn.bugstack.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GroupBuyActivity {

    private Long id;/** 自增 */

    private Long activityId;/** 活动ID */

    private String activityName;/** 活动名称 */

    private String discountId; /** 折扣ID */

    private String goodsId;/** 商品ID */

    private Integer groupType;/** 拼团方式（0自动成团、1达成目标拼团） */

    private String source;/** 来源 */

    private String channel;/** 渠道 */

    private Integer takeLimitCount; /** 拼团次数限制 */

    private Integer target;/** 拼团目标 */

    private Integer validTime;/** 拼团时长（分钟） */

    private Integer status;/** 活动状态（0创建、1生效、2过期、3废弃） */

    private Date startTime;/** 活动开始时间 */

    private Date endTime;/** 活动结束时间 */

    private String tagId;/** 人群标签规则标识 */

    private String tagScope;/** 人群标签规则范围 */

    private Date createTime;/** 创建时间 */

    private Date updateTime;/** 更新时间 */

    // 缓存标签
    public static String cacheRedisKey(Long activityId) {
        return "group_buy_market_cn.bugstack.infrastructure.dao.po.GroupBuyActivity_" + activityId;
    }

}
