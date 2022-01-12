package com.yun.springboot_redis;

import com.yun.springboot_redis.dao.QuestionDao;
import com.yun.springboot_redis.entity.Question;
import com.yun.springboot_redis.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootRedisApplicationTests {
    @Autowired
    QuestionService questionService;

    @Test
    void contextLoads() {
        List<Question> questions = questionService.queryAll();
        System.out.println(questions.size());
    }
}
