package com.yun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @FileName: User
 * @Description:
 * @Author: zyk
 * @createTime: 2021/12/24 9:46
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private int age;
    private String password;
}
