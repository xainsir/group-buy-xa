package cn.bugstack.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * SkuVO
 * @description 商品信息
 * @author xainsir
 * @date 2025/6/28 20:55
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuVO {
    /** 商品ID */
    private String goodsId;
    /** 商品名称 */
    private String goodsName;
    /** 原始价格 */
    private BigDecimal originalPrice;
}
 