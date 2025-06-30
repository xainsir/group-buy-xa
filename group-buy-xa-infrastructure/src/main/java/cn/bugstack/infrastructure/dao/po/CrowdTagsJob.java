package cn.bugstack.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * CrowdTagsJob
 * @description 人群标签任务
 * @author xainsir
 * @date 2025/6/30 17:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CrowdTagsJob {

    private Long id;/** 自增ID */

    private String tagId;/** 标签ID */

    private String batchId;/** 批次ID */

    private Integer tagType;/** 标签类型（参与量、消费金额） */

    private String tagRule;/** 标签规则（限定类型 N次） */

    private Date statStartTime;/** 统计数据，开始时间 */

    private Date statEndTime;/** 统计数据，结束时间 */

    private Integer status; /** 状态；0初始、1计划（进入执行阶段）、2重置、3完成 */

    private Date createTime;/** 创建时间 */

    private Date updateTime; /** 更新时间 */
}