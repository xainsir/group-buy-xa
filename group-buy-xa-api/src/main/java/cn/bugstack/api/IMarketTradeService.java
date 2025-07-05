package cn.bugstack.api;

import cn.bugstack.api.dto.LockMarketPayOrderRequestDTO;
import cn.bugstack.api.dto.LockMarketPayOrderResponseDTO;
import cn.bugstack.api.response.Response;

/**
 * IMarketTradeService
 * @description 营销交易服务
 * @author xainsir
 * @date 2025/7/5 16:01
 */
public interface IMarketTradeService {
    /**
     * 锁定营销支付订单
     * @return LockMarketPayOrderResponseDTO
     */
    Response<LockMarketPayOrderResponseDTO> lockMarketPayOrder(LockMarketPayOrderRequestDTO request);
}