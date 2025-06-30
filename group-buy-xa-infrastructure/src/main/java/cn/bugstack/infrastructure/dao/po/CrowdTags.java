package cn.bugstack.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * CrowdTags
 * @description 人群标签
 * @author xainsir
 * @date 2025/6/30 16:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CrowdTags {

    private Long id;/** 自增ID */

    private String tagId;/** 人群ID */

    private String tagName;/** 人群名称 */

    private String tagDesc;/** 人群描述 */

    private Integer statistics;/** 人群标签统计量 */

    private Date createTime;/** 创建时间 */

    private Date updateTime;/** 更新时间 */

}
 