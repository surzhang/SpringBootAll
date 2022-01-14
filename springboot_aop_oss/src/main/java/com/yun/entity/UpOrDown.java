package com.yun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ fileName:UpDown
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/14 21:14
 * @ version:1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpOrDown {
    private int status;
    private String message;
}
