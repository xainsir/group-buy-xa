package cn.bugstack.infrastructure.dao;

import cn.bugstack.infrastructure.dao.po.SCSkuActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * ISCSkuActivityDao
 * @description 渠道商品活动Dao
 * @author xainsir
 * @date 2025/7/1 10:04
 */
@Mapper
public interface ISCSkuActivityDao {
    SCSkuActivity querySCSkuActivityBySCGoodsId(SCSkuActivity scSkuActivity);
}