package cn.bugstack.domain.activity.model.entity;
import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TrialBalanceEntity
 * @description 试算结果实体
 * @author xainsir
 * @date 2025/6/28 18:23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrialBalanceEntity {

    private String goodsId; /** 商品ID */

    private String goodsName;/** 商品名称 */

    private BigDecimal originalPrice;/** 原始价格 */

    private BigDecimal deductionPrice; /** 折扣价格 */

    private Integer targetCount;/** 拼团目标数量 */

    private Date startTime;/** 拼团开始时间 */

    private Date endTime;/** 拼团结束时间 */

    private Boolean isVisible;/** 是否可见拼团 */

    private Boolean isEnable;/** 是否可参与进团 */

    private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;/** 活动配置信息 */
}
 