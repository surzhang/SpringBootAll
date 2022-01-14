package com.yun.controller;

import com.yun.entity.ReturnBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ fileName:AdviceController
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/16 13:14
 * @ version:1.0.0
 */
@ControllerAdvice
public class AdviceController {
    /**
     * @ author: zyk
     * @ description:前后端分离开发模式使用的统一异常处理
     * @ date: 2021/12/16 13:14
     * @ param:
     * @ return:
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ReturnBean ErrorInfo(){
        return new ReturnBean(1,"操作失败",null,null);
    }
}
