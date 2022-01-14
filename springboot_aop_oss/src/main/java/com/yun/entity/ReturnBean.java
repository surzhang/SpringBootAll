package com.yun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ fileName:ReturnBean
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/14 10:19
 * @ version:1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnBean {
    private int code;
    private String msg;
    private Long count;
    private Object data;

}
