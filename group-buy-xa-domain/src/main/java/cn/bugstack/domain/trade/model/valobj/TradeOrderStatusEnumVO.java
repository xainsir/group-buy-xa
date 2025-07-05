package cn.bugstack.domain.trade.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * TradeOrderStatusEnumVO
 * @description 交易订单状态枚举值对象
 * @author xainsir
 * @date 2025/7/5 2:42
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TradeOrderStatusEnumVO {
    CREATE(0,"初始创建"),
    COMPLETE(1,"消费完成"),
    CLOSE(2,"超时关单");
    private Integer code;
    private String info;
    public static TradeOrderStatusEnumVO valueOf(Integer code) {
        switch ( code){
            case 0:
                return CREATE;
            case 1:
                return COMPLETE;
            case 2:
                return CLOSE;
        }
        return CREATE;
    }
}