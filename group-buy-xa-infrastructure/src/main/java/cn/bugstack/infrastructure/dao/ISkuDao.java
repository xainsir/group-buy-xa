package cn.bugstack.infrastructure.dao;
import cn.bugstack.infrastructure.dao.po.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ISkuDao
 * @description 商品dao
 * @author xainsir
 * @date 2025/6/29 17:57
 */
@Mapper
public interface ISkuDao {
    Sku querySkuByGoodsId(String goodsId);
}