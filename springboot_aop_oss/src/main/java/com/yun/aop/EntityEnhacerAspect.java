package com.yun.aop;


import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ fileName:EntityEnhacerAspect
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/15 11:20
 * @ version:1.0.0
 */
@Slf4j
@EnableAspectJAutoProxy
@Component
@Aspect
public class EntityEnhacerAspect {
    /**
     * @ author: zyk
     * @ description:定义一个切点
     * @ date: 2021/12/15 11:27
     * @ param: []
     * @ return: void
     */
    @Pointcut("execution(* com.yun.controller.*.insert*(..))")
    public void saveMethod() {
    }

    @Pointcut("execution(* com.yun.controller.*.update*(..))")
    public void updateMethod() {
    }

    @Pointcut("execution(* com.yun.controller.*.query*(..))")
    public void queryMethod() {
    }

    @Around("queryMethod()")
    public Object enhance3(ProceedingJoinPoint joinPoint) {
        // 定义返回对象、得到方法需要的参数
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();
        try {
            obj = joinPoint.proceed(args);
        } catch (Throwable e) {
            log.error("统计某方法执行耗时环绕通知出错", e);
        }
        // 获取执行的方法名
        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        // 打印耗时的信息
        this.printExecTime(methodName, startTime, endTime);
        return obj;

    }
    private void printExecTime(String methodName, long startTime, long endTime) {
        long diffTime = endTime - startTime;
        log.info("-----" + methodName + " 方法执行耗时：" + diffTime + " ms");
    }

    /**
     * @ author: zyk
     * @ description:前置增强
     * @ date: 2021/12/15 11:49
     * @ param: [joinPoint]
     * @ return: void
     */
    @Before("saveMethod()")
    public void enhancer(JoinPoint joinPoint) {
        log.debug("前置方法开始执行" + new Date());
        //获取参数
        Object[] args = joinPoint.getArgs();

        //获取方法名
        String name = joinPoint.getSignature().getName();

        //修改参数
        Object arg1 = null;
        if (args != null && args.length > 0) {
            arg1 = args[0];
        }
        //增强处理第一个参数（实体类）
        ReflectUtil.invoke(arg1, "setCreateBy", "zyk");
        ReflectUtil.invoke(arg1, "setCreateTime", new Date());
    }

    @Before("updateMethod()")
    public void enhancer1(JoinPoint joinPoint) {
        log.debug("前置方法开始执行" + new Date());
        //获取参数
        Object[] args = joinPoint.getArgs();

        //获取方法名
        String name = joinPoint.getSignature().getName();

        //修改参数
        Object arg1 = null;
        if (args != null && args.length > 0) {
            arg1 = args[0];
        }
        //增强处理第一个参数（实体类）
        ReflectUtil.invoke(arg1, "setUpdateBy", "zyk");
        ReflectUtil.invoke(arg1, "setUpdateTime", new Date());
    }
}
