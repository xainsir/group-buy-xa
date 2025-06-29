package cn.bugstack.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Sku
 * @description 商品
 * @author xainsir
 * @date 2025/6/28 20:03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sku {

    private Long id;/** 自增 */
    private String goodsId;/** 商品ID */
    private String goodsName;/** 商品名称 */
    private String source;/** 来源 */
    private String channel;/** 渠道 */
    private BigDecimal originalPrice; /** 原始价格 */
    private Date createTime;/** 创建时间 */
    private Date updateTime;/** 更新时间 */
}
 