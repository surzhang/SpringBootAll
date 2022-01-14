package com.yun.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * @ fileName:SecInterceptor
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/16 11:41
 * @ version:1.0.0
 */
@Component
public class SecInterceptor implements WebRequestInterceptor {
    @Override
    public void preHandle(WebRequest request) throws Exception {
        System.out.println("WebRequestInterceptor||preHandle执行了");
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
       // model.addAttribute("username","hhhhh");
        System.out.println("WebRequestInterceptor||postHandle执行了");
    }


    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        System.out.println("WebRequestInterceptor||afterHandle执行了");
    }
}
