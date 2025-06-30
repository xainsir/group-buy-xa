package cn.bugstack.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DiscountTypeEnum
 * @description 折扣类型枚举
 * @author xainsir
 * @date 2025/6/30 13:51
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DiscountTypeEnum {
    BASE(0, "基础优惠"),
    TAG(1, "人群标签");
    private Integer code;
    private String info;
    public static DiscountTypeEnum get(Integer code){
        switch( code){
            case 0: return BASE;
            case 1: return TAG;
            default: throw new RuntimeException("折扣枚举值不存在！");
        }
    }

}
 