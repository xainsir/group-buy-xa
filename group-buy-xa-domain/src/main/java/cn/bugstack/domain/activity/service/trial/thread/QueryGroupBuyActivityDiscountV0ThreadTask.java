package cn.bugstack.domain.activity.service.trial.thread;

import java.util.Objects;
import java.util.concurrent.Callable;

import cn.bugstack.domain.activity.adapter.repository.IActivityRepository;
import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.model.valobj.SCSkuActivityVO;

/**
 * QueryGroupBuyActivityDiscountV0ThreadTask
 * @description
 * @author xainsir
 * @date 2025/6/29 18:04
 */
public class QueryGroupBuyActivityDiscountV0ThreadTask implements Callable<GroupBuyActivityDiscountVO> {

    private final String source;
    private final String channel;
    private final IActivityRepository activityRepository;
    private final String goodsId;

    public QueryGroupBuyActivityDiscountV0ThreadTask(String source, String channel, String goodsId, IActivityRepository activityRepository) {
        this.source = source;
        this.channel = channel;
        this.activityRepository = activityRepository;
        this.goodsId = goodsId;
    }
    @Override
    public GroupBuyActivityDiscountVO call() throws Exception {
        SCSkuActivityVO scSkuActivityVO =activityRepository.querySCSkuActivityBySCGoodsId(source, channel,goodsId);
        if(Objects.isNull(scSkuActivityVO))return null;
        return activityRepository.queryGroupBuyActivityDiscountVO(scSkuActivityVO.getActivityId());
    }
}