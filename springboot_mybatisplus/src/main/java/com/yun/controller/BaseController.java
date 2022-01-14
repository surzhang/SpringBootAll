package com.yun.controller;


import com.yun.entity.ReturnBean;

/**
 * @author ：Teacher陈（86521760@qq.com）
 * @date ：Created in 2021/11/24 16:33
 * @description：所有controller的父类
 * @modified By：
 * @version: 1.0
 */
public class BaseController {
    /**
     * @param object
     * @description:通用成功方法
     * @create time: 2021/11/24 16:36
     */
    public ReturnBean success(Object object, Long... count) {

        ReturnBean returnBean = new ReturnBean();
        returnBean.setCode(0);
        returnBean.setMsg("操作成功");
        returnBean.setData(object);
        if (count != null && count.length > 0) {
            returnBean.setCount(count[0]);
        }
        return returnBean;
    }
    /**

     * @description:通过返回错误方法
     * @create time: 2021/11/24 16:49
     * @param object
     * @return com.cloudwise.entity.ReturnBean
     */
    public ReturnBean fail(Object object) {

        ReturnBean returnBean = new ReturnBean();
        returnBean.setCode(1);
        returnBean.setMsg("操作失败");
        returnBean.setData(object);
        return returnBean;
    }
}
