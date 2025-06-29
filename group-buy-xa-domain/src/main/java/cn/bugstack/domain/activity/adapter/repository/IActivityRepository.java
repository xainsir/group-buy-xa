package cn.bugstack.domain.activity.adapter.repository;

import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.model.valobj.SkuVO;

/**
 * IActivityRepository
 * @description 活动仓储
 * @author xainsir
 * @date 2025/6/28 20:42
 */
public interface IActivityRepository {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(String source,String channel);// 获取活动信息
    SkuVO querySkuByGoodsId(String goodsId);// 获取商品信息

}