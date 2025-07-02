package cn.bugstack.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * TagScopeEnumVo
 * @description 人群标签枚举值对象
 * @author xainsir
 * @date 2025/7/2 13:37
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TagScopeEnumVo {

    VISIBLE(true, false, "是否允许查看拼团"),
    ENABLE(true, false, "是否参与查看拼团"),
    ;

    Boolean allow;
    Boolean refuse;
    String desc;
}