package com.yun;

import com.yun.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class SpringCacheApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    QuestionService questionService;

    @Test
    public void contextLoads() {
        String key = "test";
        //创建锁
        boolean isLock = lock(key, 1, 50);
        //判断是否获取锁
        if (isLock) {
            //获取锁失败
            log.info("获取锁失败");
            return;
        }
        try {
            log.info("获取锁成功，开始执行逻辑");
            questionService.queryAll();
            //模拟程序执行
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("程序执行异常" + e);
        } finally {
            //释放锁
            deleteLock(key);
            log.info("执行完毕，释放锁完成");
        }
    }

    //释放锁
    public void deleteLock(String key) {
        //删除key即可释放锁
        redisTemplate.delete(key);
    }

    //创建锁
    public boolean lock(String key, int value, int releaseTime) {
        //尝试获取锁
        Boolean b = redisTemplate.opsForValue().setIfAbsent(key, value, releaseTime, TimeUnit.SECONDS);
        //判断结果
        return b != null && b;
    }

}
