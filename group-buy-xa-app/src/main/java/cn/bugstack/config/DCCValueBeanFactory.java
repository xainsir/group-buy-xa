package cn.bugstack.config;

import cn.bugstack.types.annotations.DCCValue;
import cn.bugstack.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * DCCValueBeanFactory
 * @description 动态配置bean工厂
 * @author xainsir
 * @date 2025/7/2 19:08
 */
@Slf4j
@Configuration
public class DCCValueBeanFactory implements BeanPostProcessor {

    private static final String BASE_CONFIG_PATH = "group_buy_market_dcc_";
    private final RedissonClient redissonClient;
    private final Map<String, Object> dccObjGroup=new HashMap<>();

    public DCCValueBeanFactory(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }
    @Bean("dccTopic")
    public RTopic dccRedisTopicListener(RedissonClient redissonClient) {
        RTopic topic = redissonClient.getTopic("group_buy_market_dcc");
        topic.addListener(String.class, (charSequence, s) -> {

            String[] split = s.split(Constants.SPLIT);// s就是配置的key:value

            String attribute = split[0];
            String key = BASE_CONFIG_PATH + split[0];
            String value = split[1];

            RBucket<String> bucket = redissonClient.getBucket(key);
            if(!bucket.isExists())return;
            bucket.set( value);

            Object objBean=dccObjGroup.get(key);
            if(null==objBean)return;

            Class<?> objBeanClass = objBean.getClass();

            // 检查 objBean 是否是代理对象
            if(AopUtils.isAopProxy(objBean)){
                objBeanClass = AopUtils.getTargetClass(objBean);
            }
            try{
                // 1. getDeclaredField 方法用于获取指定类中声明的所有字段，包括私有字段、受保护字段和公共字段。
                // 2. getField 方法用于获取指定类中的公共字段，即只能获取到公共访问修饰符（public）的字段。
                Field field = objBeanClass.getDeclaredField(attribute);
                field.setAccessible(true);
                field.set(objBean, value);
                field.setAccessible(false);
                log.info("DCC 节点监听，动态设置值 {} {}", key, value);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        return topic;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetBeanClass = bean.getClass();
        Object targetBeanObject = bean;
        if(AopUtils.isAopProxy( targetBeanObject)){
            targetBeanClass = AopUtils.getTargetClass(targetBeanObject); // 通过Aop获取代理类的原始类
            targetBeanObject = AopProxyUtils.getSingletonTarget(targetBeanObject); // 获取原始类对象
        }
        Field[] fields = targetBeanClass.getDeclaredFields();
        for (Field field : fields) {
            if(!field.isAnnotationPresent(DCCValue.class)){ // DCCValue注解是否在当前类上
                continue;
            }
            // 如果存在就取出该注解，并解析注解值
            DCCValue dccValue = field.getAnnotation(DCCValue.class);
            String value = dccValue.value();
            if(StringUtils.isBlank(value)){
                throw new RuntimeException("DCCValue注解的value属性不能为空");
            }
            String[] split = value.split(":");
            String key = BASE_CONFIG_PATH.concat(split[0]);
            String defaultValue = split.length == 2 ? split[1] : null;
            if(StringUtils.isBlank(defaultValue)){
                throw new RuntimeException("DCCValue注解的value属性不能为空");
            }

            String setValue =defaultValue; // Redis中无值时，此值为默认值。如果有，则需要更新最新的配置值

            try {
                RBucket<String> bucket = redissonClient.getBucket(key);
                boolean exists = bucket.isExists();
                if(exists){
                    setValue = bucket.get();
                }else{
                    bucket.set(defaultValue);
                }
                field.setAccessible(true);
                field.set(targetBeanObject,setValue);
                field.setAccessible(false);

            }catch (Exception e){
                throw new RuntimeException("...");
            }
            dccObjGroup.put(key, targetBeanObject);
        }
        return bean;// 仅是对这个bean获取目标类进行aop操作
    }
}
 