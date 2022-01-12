package com.yun.springboot_redis.redis;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zyk
 * @version 1.0
 * @fileName MySpringTool
 * @description :TODO
 * @date 2022/1/11 16:19
 */
@Component
public class MySpringTool implements ApplicationContextAware {
    //自己的容器
    public static ApplicationContext myapplicationContext;

    /*
     * @param applicationContext
     * @return void
     * @description: TODO spring容器加载完成之后，获取ApplicationContext，然后做额外的操作
     * @author zyk
     * @data 2022/1/11 16:34
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        myapplicationContext = applicationContext;
    }

    /**
     * @description: TODO 通过我自己的spring容器封装某个bean
     * @author zyk
     * @param
     * @return
     * @data 2022/1/11 16:37
     */
    public static Object getBean(String id){
        Object bean = myapplicationContext.getBean(id);
        return bean;
    }
}
