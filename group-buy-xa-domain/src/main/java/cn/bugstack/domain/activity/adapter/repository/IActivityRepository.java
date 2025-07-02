package cn.bugstack.domain.activity.adapter.repository;

import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.model.valobj.SCSkuActivityVO;
import cn.bugstack.domain.activity.model.valobj.SkuVO;

/**
 * IActivityRepository
 * @description 活动仓储
 * @author xainsir
 * @date 2025/6/28 20:42
 */
public interface IActivityRepository {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId);// 获取活动信息
    SkuVO querySkuByGoodsId(String goodsId);// 获取商品信息

    SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel,String goodsId);
    boolean isTagCrowdRange(String tagId, String userId);// 增加新人群包可见逻辑

}