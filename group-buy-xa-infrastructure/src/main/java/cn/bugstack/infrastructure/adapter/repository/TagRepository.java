package cn.bugstack.infrastructure.adapter.repository;

import cn.bugstack.domain.tag.adapter.repository.ITagRepository;
import cn.bugstack.domain.tag.model.CrowdTagsJobEntity;
import cn.bugstack.infrastructure.dao.ICrowdTagsDao;
import cn.bugstack.infrastructure.dao.ICrowdTagsDetailDao;
import cn.bugstack.infrastructure.dao.ICrowdTagsJobDao;
import cn.bugstack.infrastructure.dao.po.CrowdTags;
import cn.bugstack.infrastructure.dao.po.CrowdTagsDetail;
import cn.bugstack.infrastructure.dao.po.CrowdTagsJob;
import cn.bugstack.infrastructure.redis.IRedisService;
import org.redisson.api.RBitSet;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.DuplicateFormatFlagsException;

/**
 * TagRepository
 * @description 标签仓库实现
 * @author xainsir
 * @date 2025/6/30 18:06
 */
@Repository
public class TagRepository implements ITagRepository {
    @Resource
    private ICrowdTagsDao crowdTagsDao;
    @Resource
    private ICrowdTagsJobDao crowdTagsJobDao;
    @Resource
    private ICrowdTagsDetailDao crowdTagsDetailDao;
    @Resource
    private IRedisService redisService;
    @Override
    public CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId) {

        CrowdTagsJob crowdTagsJob = crowdTagsJobDao.queryCrowdTagsJob(
                new CrowdTagsJob()
                        .setBatchId(batchId).setTagId(tagId));

        return CrowdTagsJobEntity.builder()
                .tagType(crowdTagsJob.getTagType())
                .tagRule(crowdTagsJob.getTagRule())
                .statStartTime(crowdTagsJob.getStatStartTime())
                .statEndTime(crowdTagsJob.getStatEndTime())
                .build();
    }

    @Override
    public void addCrowdTagsUserId(String tagId, String userId) {
        CrowdTagsDetail crowdTagsDetail = new CrowdTagsDetail();
        crowdTagsDetail.setUserId(userId).setTagId(tagId);
        try {
            crowdTagsDetailDao.addCrowdTagsUserId(crowdTagsDetail);
            RBitSet bitSet = redisService.getBitSet(tagId);
            bitSet.set(redisService.getIndexFromUserId(userId)); // 将用户ID转化为位置进入bitSet

        }catch (DuplicateKeyException e){
        }
    }

    @Override
    public void updateCrowdTagsStatistics(String tagId, int size) {
        CrowdTags crowdTagsReq=new CrowdTags();
        crowdTagsReq.setTagId(tagId).setStatistics(size);

        crowdTagsDao.updateCrowdTagsStatistics(crowdTagsReq);
    }
}
 