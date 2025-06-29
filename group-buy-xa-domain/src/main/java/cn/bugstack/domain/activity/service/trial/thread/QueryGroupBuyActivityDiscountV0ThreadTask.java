package cn.bugstack.domain.activity.service.trial.thread;

import java.util.concurrent.Callable;

import cn.bugstack.domain.activity.adapter.repository.IActivityRepository;
import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

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

    public QueryGroupBuyActivityDiscountV0ThreadTask(String source, String channel, IActivityRepository activityRepository) {
        this.source = source;
        this.channel = channel;
        this.activityRepository = activityRepository;
    }

    @Override
    public GroupBuyActivityDiscountVO call() throws Exception {
        return activityRepository.queryGroupBuyActivityDiscountVO(source, channel);
    }
}
 