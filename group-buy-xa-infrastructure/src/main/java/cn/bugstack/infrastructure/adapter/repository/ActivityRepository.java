package cn.bugstack.infrastructure.adapter.repository;

import cn.bugstack.domain.activity.adapter.repository.IActivityRepository;
import cn.bugstack.domain.activity.model.valobj.DiscountTypeEnum;
import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.model.valobj.SCSkuActivityVO;
import cn.bugstack.domain.activity.model.valobj.SkuVO;
import cn.bugstack.infrastructure.dao.IGroupBuyActivityDao;
import cn.bugstack.infrastructure.dao.IGroupBuyDiscountDao;
import cn.bugstack.infrastructure.dao.ISCSkuActivityDao;
import cn.bugstack.infrastructure.dao.ISkuDao;
import cn.bugstack.infrastructure.dao.po.GroupBuyActivity;
import cn.bugstack.infrastructure.dao.po.GroupBuyDiscount;
import cn.bugstack.infrastructure.dao.po.SCSkuActivity;
import cn.bugstack.infrastructure.dao.po.Sku;
import cn.bugstack.infrastructure.redis.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBitSet;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * ActivityRepository
 * @description 活动仓储实现类
 * @author xainsir
 * @date 2025/6/29 18:06
 */
@Repository
@Slf4j
public class ActivityRepository implements IActivityRepository {
    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;
    @Resource
    private IGroupBuyDiscountDao groupBuyDiscountDao;
    @Resource
    private ISkuDao skuDao;
    @Resource
    private ISCSkuActivityDao scSkuActivityDao;
    @Resource
    private IRedisService redisService;
    @Override // DB查询活动信息
    public GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId) {

        GroupBuyActivity groupBuyActivityRes = groupBuyActivityDao.queryValidGroupBuyActivityId(activityId);
        if(Objects.isNull(groupBuyActivityRes))return null;

        // 查询活动对应的营销配置
        String discountId = groupBuyActivityRes.getDiscountId();
        GroupBuyDiscount groupBuyDiscountRes = groupBuyDiscountDao.queryGroupBuyActivityDiscountByDiscountId(discountId);
        if(Objects.isNull(groupBuyDiscountRes))return null;

        // 组装数据
        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount = GroupBuyActivityDiscountVO.GroupBuyDiscount.builder()
                .discountName(groupBuyDiscountRes.getDiscountName())
                .discountDesc(groupBuyDiscountRes.getDiscountDesc())
                .discountType(DiscountTypeEnum.get(groupBuyDiscountRes.getDiscountType()))
                .marketPlan(groupBuyDiscountRes.getMarketPlan())
                .marketExpr(groupBuyDiscountRes.getMarketExpr())
                .tagId(groupBuyDiscountRes.getTagId())
                .build();

        GroupBuyActivityDiscountVO buildRes = GroupBuyActivityDiscountVO.builder()
                .activityId(groupBuyActivityRes.getActivityId())
                .activityName(groupBuyActivityRes.getActivityName())
                .groupBuyDiscount(groupBuyDiscount)
                .groupType(DiscountTypeEnum.get(groupBuyActivityRes.getGroupType()))
                .takeLimitCount(groupBuyActivityRes.getTakeLimitCount())
                .target(groupBuyActivityRes.getTarget())
                .validTime(groupBuyActivityRes.getValidTime())
                .status(groupBuyActivityRes.getStatus())
                .startTime(groupBuyActivityRes.getStartTime())
                .endTime(groupBuyActivityRes.getEndTime())
                .tagId(groupBuyActivityRes.getTagId())
                .tagScope(groupBuyActivityRes.getTagScope())
                .build();
        log.info("【活动】查询活动目标信息：{}", groupBuyActivityRes.getTarget());
        return buildRes;
    }

    @Override // DB查询SKU信息
    public SkuVO querySkuByGoodsId(String goodsId) {
        Sku sku = skuDao.querySkuByGoodsId(goodsId);
        if(Objects.isNull(sku)){
            return null;
        }
        return SkuVO.builder()
                .goodsId(sku.getGoodsId())
                .goodsName(sku.getGoodsName())
                .originalPrice(sku.getOriginalPrice())
                .build();
    }

    @Override
    public SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel, String goodsId) {
        SCSkuActivity scSkuActivityReq= new SCSkuActivity().setSource(source).setChannel( channel).setGoodsId( goodsId);
        SCSkuActivity scSkuActivity = scSkuActivityDao.querySCSkuActivityBySCGoodsId(scSkuActivityReq);
        if(Objects.isNull(scSkuActivity))return null;

        SCSkuActivityVO scSkuActivityVO = SCSkuActivityVO.builder()
                .source(scSkuActivity.getSource())
                .channel(scSkuActivity.getChannel())
                .goodsId(scSkuActivity.getGoodsId())
                .activityId(scSkuActivity.getActivityId())
                .build();
        return scSkuActivityVO;
    }

    @Override
    public boolean isTagCrowdRange(String tagId, String userId) {
        // 使用redis bitMap 判断用户是否在对应的人群包中
        RBitSet bitSet = redisService.getBitSet(tagId);
        if(!bitSet.isExists())return true;// 如果不存在该人群包，则不拦截该用户
        Boolean isWithin = bitSet.get(redisService.getIndexFromUserId(userId));
        return isWithin;// 判断用户是否在人群包中，需要先获取用户在redis中的索引
    }
}
 