package cn.bugstack.domain.activity.service.trial.node;

import cn.bugstack.domain.activity.model.entity.MarketProductEntity;
import cn.bugstack.domain.activity.model.entity.TrialBalanceEntity;
import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.model.valobj.SkuVO;
import cn.bugstack.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import cn.bugstack.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import cn.bugstack.domain.activity.service.trial.thread.QueryGroupBuyActivityDiscountV0ThreadTask;
import cn.bugstack.domain.activity.service.trial.thread.QuerySkuVOFromDBThreadTask;
import cn.bugstack.types.design.framework.tree.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * MarketNode
 * @description 营销优惠节点
 * @author xainsir
 * @date 2025/6/28 18:36
 */
@Slf4j
@Service
public class MarketNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;
    @Resource
    private EndNode endNode;

    @Override
    public void multiThread(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        // 查询拼团营销活动信息
        QueryGroupBuyActivityDiscountV0ThreadTask allTask = new QueryGroupBuyActivityDiscountV0ThreadTask(requestParameter.getSource(), requestParameter.getChannel(), activityRepository);
        FutureTask<GroupBuyActivityDiscountVO> allFutureTask = new FutureTask<>(allTask); // 创建任务
        threadPoolExecutor.execute(allFutureTask); // 执行任务

        // 查询商品信息
        QuerySkuVOFromDBThreadTask querySkuTask = new QuerySkuVOFromDBThreadTask(requestParameter.getGoodsId(), activityRepository);
        FutureTask<SkuVO> querySkuFutureTask = new FutureTask<>(querySkuTask);// 创建任务
        threadPoolExecutor.execute(querySkuFutureTask);// 执行任务

        dynamicContext.setGroupBuyActivityDiscountVO(allFutureTask.get(timeout, TimeUnit.MINUTES)); // 向上下文填充拼团营销活动结果
        dynamicContext.setSkuVO(querySkuFutureTask.get(timeout, TimeUnit.MINUTES));                 // 向上下文填充商品信息
    }
    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return router(requestParameter, dynamicContext);
    }
    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return endNode;
    }
}
 