package cn.bugstack.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserEntity
 * @description 用户实体
 * @author xainsir
 * @date 2025/7/5 2:31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private String userId;
}
 