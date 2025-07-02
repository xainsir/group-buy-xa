package cn.bugstack.domain.activity.model.valobj;
import cn.bugstack.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
/**
 * GroupBuyActivityDiscountVO
 * @description 拼团活动营销配置值对象
 * @author xainsir
 * @date 2025/6/28 20:46
 */
@Getter
@Builder
@AllArgsConstructor // 作为不需要数据库存储的数据，配置值对象即可。
@NoArgsConstructor  // 防腐设计,不需要暴露数据库结构
public class GroupBuyActivityDiscountVO {
    /** 活动ID */
    private Long activityId;

    /** 商品ID */
    private String goodsId;

    /** 活动名称 */
    private String activityName;

    /** 来源 */
    private String source;

    /** 渠道 */
    private String channel;

    /** 折扣配置 */
    private GroupBuyDiscount groupBuyDiscount;

    /** 拼团方式（0自动成团、1达成目标拼团） */
    private DiscountTypeEnum groupType;

    /** 参团限制数 */
    private Integer takeLimitCount;

    /** 参团目标数 */
    private Integer target;

    /** 标签ID */
    private String tagId;

    /** 标签作用域 */
    private String tagScope;

    /** 状态 */
    private Integer status;

    /** 有效时间 */
    private Integer validTime;

    /** 开始时间 */
    private Date startTime;

    /** 结束时间 */
    private Date endTime;

    /** 可见限制 */
    public boolean isVisible() {
        if(StringUtils.isBlank(tagScope))return TagScopeEnumVo.VISIBLE.getAllow();
        String split[]=tagScope.split(Constants.SPLIT);
        if(split.length>0 && StringUtils.isNotBlank(split[0]) && split[0].equals("1")){
            return TagScopeEnumVo.VISIBLE.getRefuse();
        }
        return TagScopeEnumVo.VISIBLE.getAllow();
    }
    /** 参与限制 */
    public boolean isEnable() {
        if(StringUtils.isBlank(tagScope))return TagScopeEnumVo.ENABLE.getAllow();
        String split[]=tagScope.split(Constants.SPLIT);
        if(split.length>1 && StringUtils.isNotBlank(split[1]) && split[1].equals("2")){
            return TagScopeEnumVo.ENABLE.getRefuse();
        }
        return TagScopeEnumVo.ENABLE.getAllow();
    }



    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GroupBuyDiscount {
        private String discountName; // 折扣标题
        private String discountDesc; // 折扣描述
        private String marketPlan;   // 折扣优惠计划（ZJ:直减、MJ:满减、N元购）
        private String marketExpr;   // 营销表达式
        private DiscountTypeEnum discountType;   // 折扣类型（0:base、1:tag）
        private String tagId;        // 人群标签，特定优惠限定
    }
}
 