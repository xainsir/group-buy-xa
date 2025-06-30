package cn.bugstack.infrastructure.dao;

import cn.bugstack.infrastructure.dao.po.CrowdTags;
import org.apache.ibatis.annotations.Mapper;

/**
 * ICrowdTagsDao
 * @description 人群标签Dao
 * @author xainsir
 * @date 2025/6/30 17:25
 */
@Mapper
public interface ICrowdTagsDao {
    // 更新人群标签总量
    void updateCrowdTagsStatistics(CrowdTags crowdTagsReq);
}
 