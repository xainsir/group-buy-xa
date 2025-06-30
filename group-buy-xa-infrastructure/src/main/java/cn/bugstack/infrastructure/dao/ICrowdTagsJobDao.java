package cn.bugstack.infrastructure.dao;

import cn.bugstack.infrastructure.dao.po.CrowdTagsJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * ICrowdTagsJobDao
 * @description 人群标签任务Dao
 * @author xainsir
 * @date 2025/6/30 17:28
 */
@Mapper
public interface ICrowdTagsJobDao {
    // 查询人群标签任务
    CrowdTagsJob queryCrowdTagsJob(CrowdTagsJob crowdTagsJob);
}