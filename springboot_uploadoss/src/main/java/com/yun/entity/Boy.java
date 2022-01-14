package com.yun.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @FileName: Boy
 * @Description:
 * @Author: zyk
 * @createTime: 2021/12/20 15:35
 * @version: 1.0
 */
@Data
@ToString
@Component("boy")
@Profile("school")
public class Boy {
    @Value("刘德华")
    private String name;
    @Value("34")
    private int age;
}
