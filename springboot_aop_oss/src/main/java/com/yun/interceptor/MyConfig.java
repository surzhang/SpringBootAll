package com.yun.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @ fileName:MyConfig
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/16 10:57
 * @ version:1.0.0
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Resource
    private MyInterceptor myInterceptor;
    @Resource
    private SecInterceptor secInterceptor;
    @Resource
    private LoginInterceptor loginInterceptor;
    /**
     * @ author: zyk
     * @ description:配置拦截器
     * @ date: 2021/12/16 11:24
     * @ param:
     * @ return:
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //查询方法拦截器
        registry.addInterceptor(myInterceptor).addPathPatterns("/dept/query*");
        //插入方法拦截器
        //registry.addWebRequestInterceptor(secInterceptor).addPathPatterns("/dept/insert*");
        //登录方法拦截器
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login",
                "/toLogin","/**/*.css","/**/*.js","/**/*.png");
    }
}
