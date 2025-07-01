package cn.bugstack.domain.activity.service.trial.node;

import cn.bugstack.domain.activity.model.entity.MarketProductEntity;
import cn.bugstack.domain.activity.model.entity.TrialBalanceEntity;
import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.model.valobj.SkuVO;
import cn.bugstack.domain.activity.service.discount.IDiscountCalculateService;
import cn.bugstack.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import cn.bugstack.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import cn.bugstack.domain.activity.service.trial.thread.QueryGroupBuyActivityDiscountV0ThreadTask;
import cn.bugstack.domain.activity.service.trial.thread.QuerySkuVOFromDBThreadTask;
import cn.bugstack.types.design.framework.tree.StrategyHandler;
import cn.bugstack.types.enums.ResponseCode;
import cn.bugstack.types.exception.AppException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
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
    @Resource
    private ErrorNode errorNode;
    @Resource
    private Map<String, IDiscountCalculateService> discountMap;
    @Autowired
    private View error;

    @Override
    public void multiThread(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        // 查询拼团营销活动信息
        QueryGroupBuyActivityDiscountV0ThreadTask allTask = new QueryGroupBuyActivityDiscountV0ThreadTask(requestParameter.getSource(), requestParameter.getChannel(),requestParameter.getGoodsId(), activityRepository);
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
        log.info("拼团商品查询试算服务-EndNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));

        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = dynamicContext.getGroupBuyActivityDiscountVO();
        if(Objects.isNull(groupBuyActivityDiscountVO)) {
            return router(requestParameter, dynamicContext);// 路由到异常节点
        }

        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount= groupBuyActivityDiscountVO.getGroupBuyDiscount();
        SkuVO skuVO = dynamicContext.getSkuVO();
        if(Objects.isNull(skuVO)||Objects.isNull(groupBuyDiscount)) {
            return router(requestParameter, dynamicContext);// 路由到异常节点
        }

        IDiscountCalculateService discountService = discountMap.get(groupBuyDiscount.getMarketPlan());
        if(Objects.isNull(discountService)) {
            throw new AppException(ResponseCode.E0001.getCode(),ResponseCode.E0001.getInfo());
        }
        // 拼团优惠试算
        BigDecimal deductionPrice = discountService.calculate(requestParameter.getUserId(), skuVO.getOriginalPrice(), groupBuyDiscount);
        dynamicContext.setDeductionPrice(deductionPrice);

        return router(requestParameter, dynamicContext);
    }
    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        // 如果groupBuyActivityDiscountVO、skuVO、groupBuyDiscount为空，则路由到异常节点
        if(Objects.isNull(dynamicContext.getGroupBuyActivityDiscountVO())
                ||Objects.isNull(dynamicContext.getSkuVO())
                ||Objects.isNull(dynamicContext.getDeductionPrice())){
            return errorNode;
        }
        return endNode;
    }
}
 