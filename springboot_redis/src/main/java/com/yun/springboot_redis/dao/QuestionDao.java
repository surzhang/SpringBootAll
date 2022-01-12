package com.yun.springboot_redis.dao;

import com.yun.springboot_redis.entity.Question;
import com.yun.springboot_redis.redis.MybatisCacheImpl;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 问题表(Question)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-10 18:56:33
 */

public interface QuestionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Question queryById(Integer id);


    List<Question> queryAll();

    /**
     * 新增数据
     *
     * @param question 实例对象
     * @return 影响行数
     */
    int insert(Question question);

    /**
     * 修改数据
     *
     * @param question 实例对象
     * @return 影响行数
     */
    int update(Question question);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

