package cn.bugstack.domain.tag.adapter.repository;

import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.model.valobj.SkuVO;
import cn.bugstack.domain.tag.model.CrowdTagsJobEntity;

/**
 * ITagRepository
 * @description 标签仓储
 * @author xainsir
 * @date 2025/6/28 20:42
 */
public interface ITagRepository {// 使用仓储结构可以加入Dao和服务接口间的隔离，防止代码耦合太强


    CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId);

    void addCrowdTagsUserId(String tagId, String userId);

    void updateCrowdTagsStatistics(String tagId, int size);
}