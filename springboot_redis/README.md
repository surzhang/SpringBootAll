# mybaits二级缓存

第一步：开启mybaits的二级缓存

```bash
#开启mybatis的二级缓存 
mybatis.configuration.cache-enabled=true 
#配置dao层的日志输出级别 
logging.level.com.yun.springboot_redis.dao=debug
```

第二步：实现mybaits的cache接口

``` java
package com.yun.springboot_redis.redis;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.Set;
/**
 * @author zyk
 * @version 1.0
 * @fileName MybatisCacheImpl
 * @description :TODO mybaits的缓存实现类
 * @date 2022/1/11 16:11
 */
@Slf4j
public class MybatisCacheImpl implements Cache {
    private RedisTemplate<String, Object> redisTemplate;
    private String id;
    /**
     * @param id
     * @description: TODO 构造器
     * @author zyk
     * @data 2022/1/11 16:13
     */
    public MybatisCacheImpl(String id) {
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public void putObject(Object key, Object value) {
        log.debug("设置缓存");
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate<String, Object>) MySpringTool.getBean("redisTemplate");
        }
        redisTemplate.opsForValue().set(key.toString(), value);
    }
    @Override
    public Object getObject(Object key) {
        log.debug("缓存的id" + this.id);
        log.debug("查询缓存");
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate<String, Object>) MySpringTool.getBean("redisTemplate");
        }
        return redisTemplate.opsForValue().get(key.toString());
    }
    /*
     * @param key
     * @description: TODO 根据key删除缓存中的内容
     * @author zyk
     * @data 2022/1/11 16:23
     */
    @Override
    public Object removeObject(Object key) {
        log.debug("删除缓存");
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate<String, Object>) MySpringTool.getBean("redisTemplate");
        }
        redisTemplate.delete(key.toString());
        return null;
    }
    /*
     * @param
     * @return void
     * @description: TODO 清空缓存
     * @author zyk
     * @data 2022/1/11 16:25
     */
    @Override
    public void clear() {
        log.debug("清空缓存");
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate<String, Object>) MySpringTool.getBean("redisTemplate");
        }
        //清空缓存就是删除所有包含该ID的key
        Set<String> keys = redisTemplate.keys("*" + this.id + "*");
        // 批量删除
        redisTemplate.delete(keys);
    }
    /*
     * 获取缓存中的所有key的size
     * @return
     */
    @Override
    public int getSize() {
        log.debug("获取缓存中的所有key的size");
        Integer execute = redisTemplate.execute(new RedisCallback<Integer>() {
            @Override
            public Integer doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize().intValue();
            }
        });
        return execute;
    }
}


```
第三步：在映射文件或者在接口上使用cache注解中引入缓存接口的实现类
```bash
<mapper namespace="com.yun.springboot_redis.dao.QuestionDao">
    <!--    eviction缓存过期策略，默认LRU（least recently use）  最近最少使用策略        FIfO  (first in  first out ) 先进先出-->
    <cache type="com.yun.springboot_redis.redis.MybatisCacheImpl" evictin="LRU">
</cache>
```
前提：有redisTemplate的bean
```java
package com.yun.springboot_redis.redis;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * @author zyk
 * @version 1.0
 * @fileName RedisConfig
 * @description :TODO
 * @date 2022/1/10 17:56
 */
@Configuration
public class RedisConfig {
    @Bean(value = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        //使用jackson序列化工具(default JDK serialization)
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        //设置任何属性可见
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //序列化的时候将类名称序列化到json串中
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        //使用redis自带的字符串序列化工具序列化key和value
        RedisSerializer redisSerializer = new StringRedisSerializer();
        //key
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        //value
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        //初始化redisTemplate
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
配置文件内容
#redis服务器的IP地址
spring.redis.host=192.168.230.180
#redis的端口号
spring.redis.port=6379

```

验截图：查询所有：![img](https://uploader.shimo.im/f/oTWd3Yp0MGt8luJS.png!thumbnail?accessToken=eyJhbGciOiJIUzI1NiIsImtpZCI6ImRlZmF1bHQiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJhY2Nlc3NfcmVzb3VyY2UiLCJleHAiOjE2NDI4NDI1OTgsImciOiJENlZLNkNRRHRLQ1lIQ3d2IiwiaWF0IjoxNjQyODQyMjk4LCJ1c2VySWQiOjU0OTg2MTQ3fQ.snTOriNKWGkPTv1Qo2BE3zLgyuq1YUVEoaKi4B0BUWA)添加：![img](https://uploader.shimo.im/f/Vo5KmIoD0ZH0ht7F.png!thumbnail?accessToken=eyJhbGciOiJIUzI1NiIsImtpZCI6ImRlZmF1bHQiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJhY2Nlc3NfcmVzb3VyY2UiLCJleHAiOjE2NDI4NDI1OTgsImciOiJENlZLNkNRRHRLQ1lIQ3d2IiwiaWF0IjoxNjQyODQyMjk4LCJ1c2VySWQiOjU0OTg2MTQ3fQ.snTOriNKWGkPTv1Qo2BE3zLgyuq1YUVEoaKi4B0BUWA) ![img](https://uploader.shimo.im/f/2CvlXOfOZCRrn3HI.png!thumbnail?accessToken=eyJhbGciOiJIUzI1NiIsImtpZCI6ImRlZmF1bHQiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJhY2Nlc3NfcmVzb3VyY2UiLCJleHAiOjE2NDI4NDI1OTgsImciOiJENlZLNkNRRHRLQ1lIQ3d2IiwiaWF0IjoxNjQyODQyMjk4LCJ1c2VySWQiOjU0OTg2MTQ3fQ.snTOriNKWGkPTv1Qo2BE3zLgyuq1YUVEoaKi4B0BUWA) 修改：![img](https://uploader.shimo.im/f/kgkaGASk5A8RDe7f.png!thumbnail?accessToken=eyJhbGciOiJIUzI1NiIsImtpZCI6ImRlZmF1bHQiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJhY2Nlc3NfcmVzb3VyY2UiLCJleHAiOjE2NDI4NDI1OTgsImciOiJENlZLNkNRRHRLQ1lIQ3d2IiwiaWF0IjoxNjQyODQyMjk4LCJ1c2VySWQiOjU0OTg2MTQ3fQ.snTOriNKWGkPTv1Qo2BE3zLgyuq1YUVEoaKi4B0BUWA) ![img](https://uploader.shimo.im/f/gSGo5aNCauLCzLsT.png!thumbnail?accessToken=eyJhbGciOiJIUzI1NiIsImtpZCI6ImRlZmF1bHQiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJhY2Nlc3NfcmVzb3VyY2UiLCJleHAiOjE2NDI4NDI1OTgsImciOiJENlZLNkNRRHRLQ1lIQ3d2IiwiaWF0IjoxNjQyODQyMjk4LCJ1c2VySWQiOjU0OTg2MTQ3fQ.snTOriNKWGkPTv1Qo2BE3zLgyuq1YUVEoaKi4B0BUWA) 删除：![img](https://uploader.shimo.im/f/pwBTNwXS4DhTtSKh.png!thumbnail?accessToken=eyJhbGciOiJIUzI1NiIsImtpZCI6ImRlZmF1bHQiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJhY2Nlc3NfcmVzb3VyY2UiLCJleHAiOjE2NDI4NDI1OTgsImciOiJENlZLNkNRRHRLQ1lIQ3d2IiwiaWF0IjoxNjQyODQyMjk4LCJ1c2VySWQiOjU0OTg2MTQ3fQ.snTOriNKWGkPTv1Qo2BE3zLgyuq1YUVEoaKi4B0BUWA) ![img](https://uploader.shimo.im/f/OV2uMAyiZD1709Ut.png!thumbnail?accessToken=eyJhbGciOiJIUzI1NiIsImtpZCI6ImRlZmF1bHQiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJhY2Nlc3NfcmVzb3VyY2UiLCJleHAiOjE2NDI4NDI1OTgsImciOiJENlZLNkNRRHRLQ1lIQ3d2IiwiaWF0IjoxNjQyODQyMjk4LCJ1c2VySWQiOjU0OTg2MTQ3fQ.snTOriNKWGkPTv1Qo2BE3zLgyuq1YUVEoaKi4B0BUWA)JMeter测试线程数100，没有缓存的情况下 ![img](https://uploader.shimo.im/f/oCoDI1dLrypjbQhn.png!thumbnail?accessToken=eyJhbGciOiJIUzI1NiIsImtpZCI6ImRlZmF1bHQiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJhY2Nlc3NfcmVzb3VyY2UiLCJleHAiOjE2NDI4NDI1OTgsImciOiJENlZLNkNRRHRLQ1lIQ3d2IiwiaWF0IjoxNjQyODQyMjk4LCJ1c2VySWQiOjU0OTg2MTQ3fQ.snTOriNKWGkPTv1Qo2BE3zLgyuq1YUVEoaKi4B0BUWA)![img](https://uploader.shimo.im/f/7NzV7HeH49CJqtI1.png!thumbnail?accessToken=eyJhbGciOiJIUzI1NiIsImtpZCI6ImRlZmF1bHQiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJhY2Nlc3NfcmVzb3VyY2UiLCJleHAiOjE2NDI4NDI1OTgsImciOiJENlZLNkNRRHRLQ1lIQ3d2IiwiaWF0IjoxNjQyODQyMjk4LCJ1c2VySWQiOjU0OTg2MTQ3fQ.snTOriNKWGkPTv1Qo2BE3zLgyuq1YUVEoaKi4B0BUWA)有缓存，线程数100![img](https://uploader.shimo.im/f/sRLrB4xhwkFwzzPC.png!thumbnail?accessToken=eyJhbGciOiJIUzI1NiIsImtpZCI6ImRlZmF1bHQiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJhY2Nlc3NfcmVzb3VyY2UiLCJleHAiOjE2NDI4NDI1OTgsImciOiJENlZLNkNRRHRLQ1lIQ3d2IiwiaWF0IjoxNjQyODQyMjk4LCJ1c2VySWQiOjU0OTg2MTQ3fQ.snTOriNKWGkPTv1Qo2BE3zLgyuq1YUVEoaKi4B0BUWA)![img](https://uploader.shimo.im/f/KfIFSYQEQdBsROPg.png!thumbnail?accessToken=eyJhbGciOiJIUzI1NiIsImtpZCI6ImRlZmF1bHQiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJhY2Nlc3NfcmVzb3VyY2UiLCJleHAiOjE2NDI4NDI1OTgsImciOiJENlZLNkNRRHRLQ1lIQ3d2IiwiaWF0IjoxNjQyODQyMjk4LCJ1c2VySWQiOjU0OTg2MTQ3fQ.snTOriNKWGkPTv1Qo2BE3zLgyuq1YUVEoaKi4B0BUWA)![img](https://uploader.shimo.im/f/OdzLl3ngQcV4iqPG.png!thumbnail?accessToken=eyJhbGciOiJIUzI1NiIsImtpZCI6ImRlZmF1bHQiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJhY2Nlc3NfcmVzb3VyY2UiLCJleHAiOjE2NDI4NDI1OTgsImciOiJENlZLNkNRRHRLQ1lIQ3d2IiwiaWF0IjoxNjQyODQyMjk4LCJ1c2VySWQiOjU0OTg2MTQ3fQ.snTOriNKWGkPTv1Qo2BE3zLgyuq1YUVEoaKi4B0BUWA)  

