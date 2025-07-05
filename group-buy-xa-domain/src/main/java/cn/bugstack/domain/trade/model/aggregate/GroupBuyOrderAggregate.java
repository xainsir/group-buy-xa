package cn.bugstack.domain.trade.model.aggregate;

import cn.bugstack.domain.trade.model.entity.PayActivityEntity;
import cn.bugstack.domain.trade.model.entity.PayDiscountEntity;
import cn.bugstack.domain.trade.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * GroupBuyOrderAggregate
 * @description 拼团订单聚合对象；聚合可以理解用各个四肢、身体、头等组装出来一个人
 * @author xainsir
 * @date 2025/7/5 2:47
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GroupBuyOrderAggregate {
    private UserEntity userEntity;
    private PayActivityEntity payActivityEntity;
    private PayDiscountEntity payDiscountEntity;

}
 