package cn.bugstack.domain.activity.service.discount.impl;

import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.service.discount.AbstractDiscountCalculateService;
import cn.bugstack.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * MJCalculateService
 * @description 满减折扣服务
 * @author xainsir
 * @date 2025/6/30 14:21
 */
@Service("MJ")
@Slf4j
public class MJCalculateService extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount discount) {

        String marketExpr = discount.getMarketExpr();// 获取满减表达式
        String[] split = marketExpr.split(Constants.SPLIT);
        BigDecimal x= new BigDecimal(split[0]);
        BigDecimal y = new BigDecimal(split[1]);
        if (originalPrice.compareTo(x) < 0) {
            return originalPrice;// 不满折扣门槛，返回原价金额
        }
        BigDecimal deductionPrice = originalPrice.subtract(y);
        if(deductionPrice.compareTo(BigDecimal.ZERO)<=0){
            return new BigDecimal("0.01");// 满减后支付金额小于等于0，需要返回0.01
        }
        return deductionPrice;
    }
}
 