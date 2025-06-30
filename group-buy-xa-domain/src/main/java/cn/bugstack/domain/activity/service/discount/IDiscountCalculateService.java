package cn.bugstack.domain.activity.service.discount;

import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

/**
 * IDiscountCalculateService
 * @description 折扣计算服务接口
 * @author xainsir
 * @date 2025/6/30 13:49
 */
public interface IDiscountCalculateService {
    BigDecimal calculate(String userId,BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount discount);
}