package cn.bugstack.domain.tag.service;

import cn.bugstack.domain.tag.adapter.repository.ITagRepository;
import cn.bugstack.domain.tag.model.CrowdTagsJobEntity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import javax.annotation.Resource;

/**
 * TagService
 * @description 标签服务接口
 * @author xainsir
 * @date 2025/6/30 17:54
 */
@Service
@Slf4j
public class TagService implements ITagService{
    @Resource
    private ITagRepository repository;
    @Override
    public void execTagBatchJob(String tagId,String batchId) {
        // 1.查询任务
        CrowdTagsJobEntity entity= repository.queryCrowdTagsJobEntity(tagId,batchId);
        // 2.根据任务采集用户数据
        List< String> userIdList =new ArrayList<String>(){{
            add("xainsir");
            add("taoz");
            add("xiaohong");
        }};
        for (String userId : userIdList){
            repository.addCrowdTagsUserId(tagId,userId);// 添加人群包框选用户
        }
        // 3.更新人群标签统计量
        repository.updateCrowdTagsStatistics(tagId,userIdList.size());
        log.info("人群标签服务-更新人群标签_{}_最新统计量：{}",tagId,userIdList.size());
    }
}
 