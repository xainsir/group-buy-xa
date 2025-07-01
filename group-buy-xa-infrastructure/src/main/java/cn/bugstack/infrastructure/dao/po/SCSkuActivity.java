package cn.bugstack.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * SCSkuActivity
 * @description 渠道商品活动配置
 * @author xainsir
 * @date 2025/7/1 9:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SCSkuActivity {

    private Long id; /** 自增主键 */

    private String source;/** 来源 */

    private String channel;/** 渠道 */

    private String goodsId;/** 商品ID */

    private Long activityId;/** 活动ID */

    private Date createTime;/** 创建时间 */

    private Date updateTime;/** 更新时间 */
}
 