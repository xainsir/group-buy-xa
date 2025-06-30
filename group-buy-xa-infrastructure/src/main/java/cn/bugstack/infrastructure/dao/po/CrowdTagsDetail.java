package cn.bugstack.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * CrowdTagsDetail
 * @description 人群标签明细
 * @author xainsir
 * @date 2025/6/30 17:18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)// 人群ID和用户映射的实体类
public class CrowdTagsDetail {

    private Long id; /** 自增ID */

    private String tagId;/** 人群ID */

    private String userId;/** 用户ID */

    private Date createTime;/** 创建时间 */

    private Date updateTime;/** 更新时间 */

}