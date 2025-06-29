package cn.bugstack.infrastructure.dao;
import cn.bugstack.infrastructure.dao.po.GroupBuyDiscount;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface IGroupBuyDiscountDao {
    List<GroupBuyDiscount> queryGroupBuyDiscountList();
    GroupBuyDiscount queryGroupBuyActivityDiscountByDiscountId(String discountId);// 根据折扣ID查询营销活动
}
