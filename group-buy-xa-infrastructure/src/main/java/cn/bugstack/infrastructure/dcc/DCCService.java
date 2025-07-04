package cn.bugstack.infrastructure.dcc;

import cn.bugstack.types.annotations.DCCValue;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * DCCValue
 * @description 动态服务类
 * @author xainsir
 * @date 2025/7/2 18:17
 */
@Service
public class DCCService {


    @DCCValue("downgradeSwitch:0")
    private String downgradeSwitch;//降级开关 0关闭、1开启

    @DCCValue("cutRange:100")
    private String cutRange; // 降级比例/切量开关 100%全量

    public Boolean isDowngradeSwitch(){
        return "1".equals(downgradeSwitch);
    }
    public Boolean isCutRange(String userId){// 给定用户id，通过哈希计算后取尾号后俩位看是否在切量值内
        // 计算用户ID哈希值
        int hash = Math.abs(userId.hashCode());

        // 获取哈希值后俩位
        int lastTwoDigits = hash % 100;

        return lastTwoDigits <= Integer.parseInt(cutRange);
    }
}