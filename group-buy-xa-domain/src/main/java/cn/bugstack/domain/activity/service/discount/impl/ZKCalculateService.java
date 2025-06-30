package cn.bugstack.domain.activity.service.discount.impl;

import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.service.discount.AbstractDiscountCalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * ZJCalculateService
 * @description 普通打折扣服务
 * @author xainsir
 * @date 2025/6/30 14:28
 */
@Service("ZK")
@Slf4j
public class ZKCalculateService extends AbstractDiscountCalculateService {
    @Override
    protected BigDecimal doCalculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount discount) {

        String marketExpr = discount.getMarketExpr();
        BigDecimal deductionPrice = originalPrice.multiply(new BigDecimal(marketExpr));

        if(deductionPrice.compareTo(BigDecimal.ZERO)<=0){
            return new BigDecimal("0.01");// 打折后支付金额小于等于0，需要返回0.01
        }
        return deductionPrice;
    }
}
 