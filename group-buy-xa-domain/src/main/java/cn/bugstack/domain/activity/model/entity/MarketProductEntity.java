package cn.bugstack.domain.activity.model.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * MarketProductEntity
 * @description 营销商品实体
 * @author xainsir
 * @date 2025/6/28 18:23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketProductEntity {

    private String userId;/** 用户ID */

    private String goodsId;/** 商品ID */

    private String source;/** 渠道 */

    private String channel;/** 来源 */

    private Long activityId;/** 活动ID */

}
 