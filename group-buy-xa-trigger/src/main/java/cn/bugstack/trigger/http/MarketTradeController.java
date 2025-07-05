package cn.bugstack.trigger.http;

import cn.bugstack.api.IMarketTradeService;
import cn.bugstack.api.dto.LockMarketPayOrderRequestDTO;
import cn.bugstack.api.dto.LockMarketPayOrderResponseDTO;
import cn.bugstack.api.response.Response;
import cn.bugstack.domain.activity.model.entity.MarketProductEntity;
import cn.bugstack.domain.activity.model.entity.TrialBalanceEntity;
import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.service.IIndexGroupBuyMarketService;
import cn.bugstack.domain.trade.model.entity.MarketPayOrderEntity;
import cn.bugstack.domain.trade.model.entity.PayActivityEntity;
import cn.bugstack.domain.trade.model.entity.PayDiscountEntity;
import cn.bugstack.domain.trade.model.entity.UserEntity;
import cn.bugstack.domain.trade.model.valobj.GroupBuyProgressVO;
import cn.bugstack.domain.trade.service.ITradeOrderService;
import cn.bugstack.types.enums.ResponseCode;
import cn.bugstack.types.exception.AppException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * MarketTradeController
 * @description 营销交易控制器
 * @author xainsir
 * @date 2025/7/5 16:17
 */
@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/gbm/trade/")
public class MarketTradeController implements IMarketTradeService {
    @Resource// 试算服务类
    private IIndexGroupBuyMarketService indexGroupBuyMarketService;
    @Resource// 营销订单服务类
    private ITradeOrderService tradeOrderService;

    @Override
    public Response<LockMarketPayOrderResponseDTO> lockMarketPayOrder(LockMarketPayOrderRequestDTO request) {
        try {
            // 给出营销锁单参数：商品、用户、活动、拼团、渠道、来源、订单号
            String userId=request.getUserId();
            String teamId=request.getTeamId();
            Long activityId=request.getActivityId();
            String goodsId=request.getGoodsId();
            String source=request.getSource();
            String channel=request.getChannel();
            String outTradeNo=request.getOutTradeNo();

            log.info("营销交易锁单:{} LockMarketPayOrderRequestDTO:{}", userId, JSON.toJSONString(request));
            // 一旦上述参数其中有一为空，即返回ILLEGAL_PARAMETER应答
            if (StringUtils.isEmpty(userId) || null == activityId
                    || StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(source)
                    || StringUtils.isEmpty(channel) || StringUtils.isEmpty(outTradeNo)) {

                return Response.<LockMarketPayOrderResponseDTO>builder()
                        .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                        .info(ResponseCode.ILLEGAL_PARAMETER.getInfo())
                        .build();
            }
            // 查询 outTradeNo 是否已经存在交易记录
            MarketPayOrderEntity marketPayOrderEntity = tradeOrderService.queryNoPayMarketOrderByOutTradeNo(userId, outTradeNo);
            // 倘若存在记录，则组装锁单营销支付订单应答体返回结果
            if(!Objects.isNull(marketPayOrderEntity)){
                // 构建LockMarketPayOrderResponseDTO
                LockMarketPayOrderResponseDTO lockMarketPayOrderResponseDTO = LockMarketPayOrderResponseDTO.builder()
                                .orderId(marketPayOrderEntity.getOrderId())
                                .deductionPrice(marketPayOrderEntity.getDeductionPrice())
                                .tradeOrderStatus(marketPayOrderEntity.getTradeOrderStatusEnumVO().getCode())
                                .build();

                log.info("交易锁单记录(存在):{} marketPayOrderEntity:{}", userId, JSON.toJSONString(marketPayOrderEntity));
                return Response.<LockMarketPayOrderResponseDTO>builder()
                        .code(ResponseCode.SUCCESS.getCode())
                        .info(ResponseCode.SUCCESS.getInfo())
                        .data(lockMarketPayOrderResponseDTO)
                        .build();
            }
            // 如果不存在，判断下拼团锁单是否完成目标，即是否还有名额
            if(!Objects.isNull(teamId)){
                GroupBuyProgressVO groupBuyProgressVO = tradeOrderService.queryGroupBuyProgress(teamId);
                if(!Objects.isNull(groupBuyProgressVO)&&Objects.equals(groupBuyProgressVO.getCompleteCount(), groupBuyProgressVO.getTargetCount())){
                    log.info("交易锁单拦截-拼单目标已达成:{} {}", userId, teamId);
                    return Response.<LockMarketPayOrderResponseDTO>builder()
                            .code(ResponseCode.E0006.getCode())
                            .info(ResponseCode.E0006.getInfo())
                            .build();
                }
            }
            // 拼团名额尚存，进行营销试算
            TrialBalanceEntity trialBalanceEntity = indexGroupBuyMarketService.indexMarketTrial(
                    MarketProductEntity.builder()
                            .userId(userId)
                            .channel(channel)
                            .source(source)
                            .activityId(activityId)
                            .goodsId(goodsId)
                            .build());
            GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = trialBalanceEntity.getGroupBuyActivityDiscountVO();

             // 进行锁单
             marketPayOrderEntity = tradeOrderService.lockMarketPayOrder(
                    UserEntity.builder()
                            .userId(userId)
                            .build(),
                    PayActivityEntity.builder()
                            .teamId(teamId)
                            .activityId(activityId)
                            .activityName(groupBuyActivityDiscountVO.getActivityName())
                            .startTime(groupBuyActivityDiscountVO.getStartTime())
                            .endTime(groupBuyActivityDiscountVO.getEndTime())
                            .targetCount(groupBuyActivityDiscountVO.getTarget())
                            .build(),
                    PayDiscountEntity.builder()
                            .source(source)
                            .channel(channel)
                            .goodsId(goodsId)
                            .goodsName(trialBalanceEntity.getGoodsName())
                            .originalPrice(trialBalanceEntity.getOriginalPrice())
                            .deductionPrice(trialBalanceEntity.getDeductionPrice())
                            .outTradeNo(outTradeNo)
                            .build());

            log.info("交易锁单记录(新):{} marketPayOrderEntity:{}", userId, JSON.toJSONString(marketPayOrderEntity));

            // 返回结果
            return Response.<LockMarketPayOrderResponseDTO>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(LockMarketPayOrderResponseDTO.builder()
                            .orderId(marketPayOrderEntity.getOrderId())
                            .deductionPrice(marketPayOrderEntity.getDeductionPrice())
                            .tradeOrderStatus(marketPayOrderEntity.getTradeOrderStatusEnumVO().getCode())
                            .build())
                    .build();



        }catch (AppException e){
            log.error("营销交易锁单业务异常:{} LockMarketPayOrderRequestDTO:{}", request.getUserId(), JSON.toJSONString(request), e);
            return Response.<LockMarketPayOrderResponseDTO>builder()
                    .code(e.getCode())
                    .info(e.getInfo())
                    .build();
        }
        catch (Exception e){
            log.error("营销交易锁单服务失败:{} LockMarketPayOrderRequestDTO:{}", request.getUserId(), JSON.toJSONString(request), e);
            return Response.<LockMarketPayOrderResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }
}
 