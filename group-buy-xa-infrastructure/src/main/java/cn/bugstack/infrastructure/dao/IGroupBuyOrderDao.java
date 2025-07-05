package cn.bugstack.infrastructure.dao;

import cn.bugstack.infrastructure.dao.po.GroupBuyOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * IGroupBuyOrderDao
 * @description 用户拼单dao
 * @author xainsir
 * @date 2025/7/5 2:06
 */
@Mapper
public interface IGroupBuyOrderDao {
    // 插入对象
    int insert(GroupBuyOrder record);
    // 更新增加锁定
    int updateAddLockCount(String teamId);
    // 更新减少锁定
    int updateSubtractionLockCount(String teamId);
    // 查询拼单进度
    GroupBuyOrder queryGroupBuyProgress(String teamId);
}