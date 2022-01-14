package com.yun.condition;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @FileName: LinuxCondition
 * @Description: Linux操作系统的匹配条件
 * @Author: zyk
 * @createTime: 2021/12/20 18:02
 * @version: 1.0
 */
public class LinuxCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("os.name").contains("Linux");
    }
}
