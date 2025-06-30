package cn.bugstack.infrastructure.dao;

import cn.bugstack.infrastructure.dao.po.CrowdTagsDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * ICrowdTagsDetailDao
 * @description 人群标签明细Dao
 * @author xainsir
 * @date 2025/6/30 17:27
 */
@Mapper
public interface ICrowdTagsDetailDao {
    // 增加新用户关联人群标签
    void addCrowdTagsUserId(CrowdTagsDetail crowdTagsDetail);
}