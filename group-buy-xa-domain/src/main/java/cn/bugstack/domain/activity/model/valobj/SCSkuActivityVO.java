package cn.bugstack.domain.activity.model.valobj;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

/**
 * SCSkuActivityVO
 * @description SC商品活动值对象
 * @author xainsir
 * @date 2025/7/1 15:59
 */
@Getter
@Builder
@AllArgsConstructor // 作为不需要数据库存储的数据，配置值对象即可。
@NoArgsConstructor  // 防腐设计,不需要暴露数据库结构
@Accessors(chain = true)
public class SCSkuActivityVO {

    private String source;/** 来源 */

    private String channel;/** 渠道 */

    private String goodsId;/** 商品ID */

    private Long activityId;/** 活动ID */
}
 