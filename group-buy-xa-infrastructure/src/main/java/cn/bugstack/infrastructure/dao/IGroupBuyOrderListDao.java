package cn.bugstack.infrastructure.dao;

import cn.bugstack.infrastructure.dao.po.GroupBuyOrderList;
import org.apache.ibatis.annotations.Mapper;

/**
 * IGroupBuyOrderListDao
 * @description 用户拼单明细Dao
 * @author xainsir
 * @date 2025/7/5 2:07
 */
@Mapper
public interface IGroupBuyOrderListDao {
    //插入对象
    int insert(GroupBuyOrderList groupBuyOrderListReq);
    //通过外部订单号查询对象
    GroupBuyOrderList queryGroupBuyOrderRecordByOutTradeNo(GroupBuyOrderList groupBuyOrderListReq);
}