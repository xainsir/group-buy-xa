package cn.bugstack.test.domain.tag;

import cn.bugstack.domain.tag.service.ITagService;
import cn.bugstack.infrastructure.redis.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBitSet;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * ITagServiceTest
 * @description 标签测试类
 * @author xainsir
 * @date 2025/6/30 18:54
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ITagServiceTest {
    @Resource
    private ITagService tagService;
    @Resource
    private IRedisService redisService;

    @Test
    public void test_execTagBatchJob() throws Exception {
        tagService.execTagBatchJob("RQ_KJHKL98UU78H66554GFDV", "10001");
    }
    @Test
    public void test_getIndexFromUserId() throws Exception {
        RBitSet bitSet = redisService.getBitSet("RQ_KJHKL98UU78H66554GFDV");
        log.info("用户名：{}，是否被框选人群包：{}", "xainsir", bitSet.get(redisService.getIndexFromUserId("xainsir")));
        log.info("用户名：{}，是否被框选人群包：{}", "taoz", bitSet.get(redisService.getIndexFromUserId("taoz")));
        log.info("用户名：{}，是否被框选人群包：{}", "xiaohong", bitSet.get(redisService.getIndexFromUserId("xiaohong")));
        log.info("用户名：{}，是否被框选人群包：{}", "xiaoming", bitSet.get(redisService.getIndexFromUserId("xiaoming")));
    }
}
 