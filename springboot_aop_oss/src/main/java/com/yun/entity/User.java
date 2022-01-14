package com.yun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ fileName:User
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/16 15:16
 * @ version:1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String name;
    private String password;
}
