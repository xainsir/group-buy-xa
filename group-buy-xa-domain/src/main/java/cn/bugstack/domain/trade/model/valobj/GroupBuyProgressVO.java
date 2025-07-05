package cn.bugstack.domain.trade.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * GroupBuyProgressVO
 * @description 拼团进度值对象
 * @author xainsir
 * @date 2025/7/5 3:10
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyProgressVO {
    private Integer targetCount;   /** 目标数量 */
    private Integer completeCount; /** 完成数量 */
    private Integer lockCount;     /** 锁定数量 */

}
 