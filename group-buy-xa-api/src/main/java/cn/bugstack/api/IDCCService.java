package cn.bugstack.api;

import cn.bugstack.api.response.Response;

/**
 * IDCCService
 * @description 动态配置开关服务类
 * @author xainsir
 * @date 2025/7/2 19:05
 */
public interface IDCCService {
    Response<Boolean> updateConfig(String key, String value);
}