package com.yun.springboot_redis.test;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.springboot_redis.entity.Dept;
import com.yun.springboot_redis.entity.Question;
import com.yun.springboot_redis.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author zyk
 * @version 1.0
 * @fileName RedisTemplateTest
 * @description :TODO
 * @date 2022/1/10 17:37
 */
@Slf4j
@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    QuestionService questionService;

    /**
     * @param
     * @return void
     * @description: TODO
     * @author zyk
     * @data 2022/1/10 18:24
     */
    @Test
    public void templateTest() throws IOException {
        Dept dept = new Dept();
        dept.setCreateBy("小z");
        dept.setCreateTime(new Date());
        dept.setDeptName("运维6部");
        dept.setDeptId(1000);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("dept.json"), dept);
    }

    @Test
    public void toObject() throws IOException {
        //jackson将json字符串转换成java对象
        /* ObjectMapper objectMapper = new ObjectMapper();
        Dept dept = objectMapper.readValue(new File("dept.json"),Dept.class);
        System.out.println(dept.toString());*/

        //jackson将json转换成java集合(object)
       /* ObjectMapper objectMapper = new ObjectMapper();
        ArrayList arrayList = objectMapper.readValue(new File("deptList.json"), ArrayList.class);
        for (Object o : arrayList) {
            System.out.println(o);
        }*/

        //jackson将json转换成java集合(JavaType)
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Dept.class);
        List<Dept> deptList = objectMapper.readValue(new File("deptList.json"), javaType);
        for (Dept dept : deptList) {
            System.out.println(dept.toString());
        }
    }

    @Test
    public void redisTemplateTest() {
    /*    //获取key
        Object aaa = redisTemplate.opsForValue().get("aaa");
        System.out.println(aaa);
        Object bbb = redisTemplate.opsForValue().get("bbb");
        System.out.println(bbb);
        // 校验是否含有key

        Boolean aBoolean = redisTemplate.hasKey("aaa");
        Boolean cBoolean = redisTemplate.hasKey("ccc");
        System.out.println(aBoolean + "||" + cBoolean);
        //删除单个key
        Boolean delete = redisTemplate.delete("bbb");
        System.out.println(delete);
        redisTemplate.opsForValue().set("ddd", 9000);*/
        Set<String> keys = new HashSet<>();
        keys.add("aaa");
        keys.add("ddd");
        //批量删除
        Long delete = redisTemplate.delete(keys);
        System.out.println("删除成功的条数：" + delete);
    }

    //使用jackson保存所有的测试题到question.json文件中。
    @Test
    public void questionToJson() throws IOException {
        List<Question> list = questionService.queryAll();
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.writeValue(new File("questionList.json"),list);
    }
    //使用jackson读取question.json,保存到list<question>.
    @Test
    public void JsonToQuestion() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Question.class);
        List<Question> deptList = objectMapper.readValue(new File("questionList.json"), javaType);
        for (Question question : deptList) {
            System.out.println(question.toString());
        }
    }

}
