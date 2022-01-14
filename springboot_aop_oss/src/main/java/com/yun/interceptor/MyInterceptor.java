package com.yun.interceptor;

import cn.hutool.core.date.StopWatch;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ fileName:MyInterceptor
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/16 10:57
 * @ version:1.0.0
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    StopWatch stopWatch=new StopWatch();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { System.out.println("拦截器前置方法开始执行");
        if (stopWatch==null){
            stopWatch=new StopWatch();
        }
    stopWatch.start();
       return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("后置增强开始执行");
        stopWatch.stop();
        System.out.println(handler.toString()+"花费时间："+stopWatch.getTotalTimeMillis()+"ms");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("最终增强通知");
        stopWatch=null;
    }
}
