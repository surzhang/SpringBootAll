package com.yun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.yun.dao"})
public class SpringbootAopOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAopOssApplication.class, args);
    }

}
