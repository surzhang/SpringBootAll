package com.yun.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @FileName: WindowsCondition
 * @Description:
 * @Author: zyk
 * @createTime: 2021/12/20 17:59
 * @version: 1.0
 */
public class WindowsCondition implements Condition {
    /**
     * @Author: zyk
     * @Description: 返回true就是匹配
     * @Date: 2021/12/20 18:00
     * @Param: [context, metadata]
     * @return: boolean
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("os.name").contains("Window");
    }
}
