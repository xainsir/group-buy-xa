package cn.bugstack.domain.tag.service;

/**
 * ITagService
 * @description 标签服务接口类
 * @author xainsir
 * @date 2025/6/30 17:52
 */
public interface ITagService {
    //执行标签批量任务
    void execTagBatchJob(String tagId,String batchId);
}