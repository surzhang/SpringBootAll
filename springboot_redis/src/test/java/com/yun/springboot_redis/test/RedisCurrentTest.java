package com.yun.springboot_redis.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;

import javax.lang.model.util.ElementScanner6;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zyk
 * @version 1.0
 * @fileName RedisCurrentTest
 * @description :TODO
 * @date 2022/1/10 19:37
 */
@SpringBootTest
public class RedisCurrentTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void StringTest() {
        //判断是否有key所对应的值，有则返回true，没有则返回false
        Boolean hasKey = redisTemplate.hasKey("a1");
        System.out.println(hasKey);//true
        //有则取出key值所对应的值
        redisTemplate.opsForValue().get("a1");
        System.out.println(hasKey);
        //删除单个key值
        Boolean delete = redisTemplate.delete("a1");
        System.out.println(delete);
        //批量删除
        Collection<String> strings = new ArrayList<>();
        strings.add("a2");
        strings.add("a3");
        Long delete1 = redisTemplate.delete(strings);
        System.out.println(delete1);

        //设置过期时间
        Boolean a5 = redisTemplate.expire("a5", 60, TimeUnit.SECONDS);
        System.out.println(a5);

        //返回当前key所对应的剩余过期时间
        Long a51 = redisTemplate.getExpire("a5");
        System.out.println(a51);
    }

    @Test
    public void HashTest() {
        //新增hashMap值
        redisTemplate.opsForHash().put("myhash", "h1", "1");
        //以map集合的形式添加键值对
        Map<String, String> maps = new HashMap<>();
        maps.put("h2", "2");
        maps.put("h3", "3");
        maps.put("h4", "4");
        maps.put("h5", "5");
        redisTemplate.opsForHash().putAll("myhash", maps);

        //获取变量中的指定map键是否有值,如果存在该map键则获取值，没有则返回null。
        Object o = redisTemplate.opsForHash().get("myhash", "h1");
        System.out.println(o);
        //获取变量中的键值对
        Map myhash = redisTemplate.opsForHash().entries("myhash");
        for (Object object : myhash.keySet()) {
            System.out.print(object + ",");
        }

        //删除key
        Long delete = redisTemplate.opsForHash().delete("myhash", "h4");
        System.out.println(delete);


    }


    @Test
    public void ListTest() {
        //存值
        //存储在list的头部，即添加一个就把它放在最前面的索引处
        redisTemplate.opsForList().leftPush("tList", "a");

        //把多个值存入List中(value可以是多个值，也可以是一个Collection value)
        redisTemplate.opsForList().leftPushAll("tList", "b", "c", "d");

        //List存在的时候再加入
        redisTemplate.opsForList().leftPushIfPresent("tList", "k");

        //如果pivot处值存在则在pivot前面添加
        redisTemplate.opsForList().leftPush("tList", "c", "m");

        //按照先进先出的顺序来添加(value可以是多个值，或者是Collection var2)
        redisTemplate.opsForList().rightPush("tList", "l");
        redisTemplate.opsForList().rightPushAll("tList", "n");

        //在pivot元素的右边添加值
        redisTemplate.opsForList().rightPush("tList", "b", "p");
        //获取元素
        //获取列表指定范围内的元素(start开始位置, 0是开始位置，end 结束位置, -1返回所有)
        List tList = redisTemplate.opsForList().range("tList", 0, -1);
        for (Object obj : tList) {
            System.out.println(obj);
        }
        //移除并获取列表中第一个元素(如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止)
        Object tList1 = redisTemplate.opsForList().leftPop("tList");
        System.out.println(tList1);

    }

    @Test
    public void SetTest() {
        //添加元素
        redisTemplate.opsForSet().add("tSet", "1", "33", "66", "5", "8");

        //移除元素(单个值、多个值)
        redisTemplate.opsForSet().remove("tSet", "8");

        //删除并且返回一个随机的元素
        redisTemplate.opsForSet().pop("tSet");

        //获取集合的大小
        redisTemplate.opsForSet().size("tSet");

        //判断集合是否包含value
        Boolean tSet = redisTemplate.opsForSet().isMember("tSet", "1");
        System.out.println(tSet);


        //获取两个集合的交集(key对应的无序集合与otherKey对应的无序集合求交集)
        redisTemplate.opsForSet().intersect("tSet", "myset");

        //获取多个集合的交集(Collection var2)
        redisTemplate.opsForSet().intersect("tSet", "myset");

        //获取集合中的所有元素
        Set tSet1 = redisTemplate.opsForSet().members("tSet");
        for (Object s : tSet1) {
            System.out.print(s + " ");
        }

    }
    @Test
    public void ZSetTest() {
        //添加元素(有序集合是按照元素的score值由小到大进行排列)
        redisTemplate.opsForZSet().add("tzset", "a", 88);
        redisTemplate.opsForZSet().add("tzset", "b", 99);
        redisTemplate.opsForZSet().add("tzset", "c", 15);
        redisTemplate.opsForZSet().add("tzset", "d", 55);

//删除对应的value,value可以为多个值
        redisTemplate.opsForZSet().remove("tzset", "a");

//增加元素的score值，并返回增加后的值
        redisTemplate.opsForZSet().incrementScore("tzset", "c", 2);

//返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
        Long rank = redisTemplate.opsForZSet().rank("tzset", "c");
        System.out.println("从小到大排名c:"+rank);

        //返回元素在集合的排名,按元素的score值由大到小排列
        Long aLong = redisTemplate.opsForZSet().reverseRank("tzset", "c");
        System.out.println("从大到小排名c:"+aLong);

        //获取集合中给定区间的元素(start 开始位置，end 结束位置, -1查询所有)
        Set tzset = redisTemplate.opsForZSet().reverseRangeWithScores("tzset", 0, -1);
        for (Object obj:tzset) {
            System.out.println(obj);
        }


        //移除指定索引位置处的成员
        redisTemplate.opsForZSet().removeRange("tzset",0, 1);



    }
}
