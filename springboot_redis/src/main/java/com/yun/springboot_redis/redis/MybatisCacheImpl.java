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
