package com.yun.entity;


import lombok.Data;

/**
 * @date ：Created in 2021/11/24 15:02
 * @description：layui数据表格专用的实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class ReturnBean{
    private int code;
    private String msg;
    private Long count;
    private Object data;
}
