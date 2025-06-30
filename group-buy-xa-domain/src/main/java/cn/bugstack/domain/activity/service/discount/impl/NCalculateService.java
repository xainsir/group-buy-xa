package cn.bugstack.domain.activity.service.discount.impl;

import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.service.discount.AbstractDiscountCalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * NCalculateService
 * @description N元购折扣服务
 * @author xainsir
 * @date 2025/6/30 14:27
 */
@Service("N")
@Slf4j
public class NCalculateService extends AbstractDiscountCalculateService {
    @Override
    protected BigDecimal doCalculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount discount) {

        String marketExpr = discount.getMarketExpr();// 获取折扣表达式
        return new BigDecimal(marketExpr);
    }
}
 