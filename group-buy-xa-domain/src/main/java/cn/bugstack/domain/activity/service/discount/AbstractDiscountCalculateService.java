package cn.bugstack.domain.activity.service.discount;

import cn.bugstack.domain.activity.model.valobj.DiscountTypeEnum;
import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

/**
 * AbstractDiscountCalculateService
 * @description 抽象折扣计算基类
 * @author xainsir
 * @date 2025/6/30 14:14
 */
// 用于过滤折扣的人群标签，将折扣初始接口及其信息更进一步封装
public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService {
    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount discount) {
        // 如果折扣类型属于人群标签筛选
        if (DiscountTypeEnum.TAG.equals(discount.getDiscountType())) {
            boolean isCrowdRange= filterTagId(userId, discount.getTagId());
            // 不在人群标签内
            if (!isCrowdRange) {
                return originalPrice;
            }
        }
        return doCalculate(userId, originalPrice, discount);


    }
    // 抽象方法，哪个子类业务需要再去实现
    protected abstract BigDecimal doCalculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount discount);

    private boolean filterTagId(String userId, String tagId) {
        return true;
    }
}
 