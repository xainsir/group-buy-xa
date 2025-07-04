package cn.bugstack.types.annotations;

import java.lang.annotation.*;

/**
 * DCCValue
 * @description 动态配置开关
 * @author xainsir
 * @date 2025/7/2 18:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface DCCValue {
    
    String value() default "";

}