package com.yun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @FileName: SwaggerConfig
 * @Description:
 * @Author: zyk
 * @createTime: 2021/12/24 9:31
 * @version: 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private ApiInfo apiInfo(){
        Contact contact = new Contact("张艳坤","http://localhost:8080","3296252248@qq.com");
        return new ApiInfo("我的第一个swaggger文档","这是个api文档","1.0","http://localhost:8080",
                contact,"apache2.0","http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
    /**
     * @Author: zyk
     * @Description: 配置swagger的docker的bean实例
     * @Date: 2021/12/24 9:31
     * @Param: 
     * @return: 
     */
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }
}
