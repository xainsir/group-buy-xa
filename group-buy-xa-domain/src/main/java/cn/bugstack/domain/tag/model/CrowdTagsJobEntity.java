package cn.bugstack.domain.tag.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * CrowdTagsJobEntity
 * @description 人群标签任务实体
 * @author xainsir
 * @date 2025/6/30 17:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrowdTagsJobEntity {

    private Integer tagType;/** 标签类型（参与量、消费金额） */

    private String tagRule;/** 标签规则（限定类型 N次） */

    private Date statStartTime;/** 统计数据，开始时间 */

    private Date statEndTime;/** 统计数据，结束时间 */
}
 