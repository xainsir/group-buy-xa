package cn.bugstack.trigger.http;

import cn.bugstack.api.IDCCService;
import cn.bugstack.api.response.Response;
import cn.bugstack.types.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * DCCService
 * @description 动态配置控制服务类
 * @author xainsir
 * @date 2025/7/4 16:00
 */
@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/gbm/dcc/")
public class DCCController implements IDCCService {
    @Resource
    private RTopic dccTopic;

    /**
     * 动态值变更
     * <p>
     * curl http://127.0.0.1:8099/api/v1/gbm/dcc/update_config?key=downgradeSwitch&value=1
     * curl http://127.0.0.1:8099/api/v1/gbm/dcc/update_config?key=cutRange&value=0
     */
    @Override
    @GetMapping("update_config")
    public Response<Boolean> updateConfig(@RequestParam String key,@RequestParam String value) {
        try {
            dccTopic.publish(key+","+value);
            return Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .build();
        }catch (Exception e){
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }
}
 