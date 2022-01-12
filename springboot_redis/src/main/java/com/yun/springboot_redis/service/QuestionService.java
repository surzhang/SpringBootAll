package com.yun.springboot_redis.service;

import com.yun.springboot_redis.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 问题表(Question)表服务接口
 *
 * @author makejava
 * @since 2022-01-10 18:56:33
 */
public interface QuestionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Question queryById(Integer id);


    /**
     * 新增数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    Question insert(Question question);

    /**
     * 修改数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    Question update(Question question);

    /**
     * @param
     * @return java.util.List<com.yun.springboot_redis.entity.Question>
     * @description: 查询所有数据
     * @author zyk
     * @data 2022/1/10 19:01
     */
    List<Question> queryAll();

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
