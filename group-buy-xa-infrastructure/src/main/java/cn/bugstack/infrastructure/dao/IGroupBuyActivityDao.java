package cn.bugstack.infrastructure.dao;

import cn.bugstack.infrastructure.dao.po.GroupBuyActivity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IGroupBuyActivityDao {
    List<GroupBuyActivity> queryGroupBuyActivityList();
    GroupBuyActivity queryValidGroupBuyActivity(GroupBuyActivity groupBuyActivity);// 查询有效的活动
}
