package com.yun.service.impl;

import com.yun.entity.Question;
import com.yun.dao.QuestionDao;
import com.yun.service.QuestionService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 问题表(Question)表服务实现类
 *
 * @author makejava
 * @since 2022-01-12 17:08:25
 */
//开启注解
@Service("questionService")
@CacheConfig(cacheNames = "zykCache")
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionDao questionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    //打开注解
    @Cacheable
    @Override
    public Question queryById(Integer id) {
        return this.questionDao.queryById(id);
    }

    //打开缓存注解
    @Cacheable
    @Override
    public List<Question> queryAll() {
        return this.questionDao.queryAll();
    }

    /**
     * 分页查询
     *
     * @param question    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Question> queryByPage(Question question, PageRequest pageRequest) {
        long total = this.questionDao.count(question);
        return new PageImpl<>(this.questionDao.queryAllByLimit(question, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     * 添加热点数据，想立即放入缓存，可以在insert方法上使用CachePut
     * @param question 实例对象
     * @return 实例对象
     */
   // @CachePut(key = "#root.methodName+'_'+#question.id")
    //#root.target调用对象   #root.targetClass获取类的全路径
    //@CachePut(key = "#root.targetClass+'_'+#question.id")
    @CacheEvict(key = "#question.id",allEntries = true,beforeInvocation = false)
    @Override
    public Question insert(Question question) {
        this.questionDao.insert(question);
        return question;
    }

    /**
     * 修改数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    //@CachePut(key = "#question.id")
    @CacheEvict(key = "#question.id",allEntries = true,beforeInvocation = false)
    @Override
    public Question update(Question question) {
        this.questionDao.update(question);
        return this.queryById(question.getId());
    }

    /**
     * 通过主键删除数据
     *allEntries = true 删除命名空间下的所有缓存
     *beforeInvocation = true 先删除缓存
     * @param id 主键
     * @return 是否成功
     */
    @CacheEvict(key = "#id",allEntries = true,beforeInvocation = false)
    @Override
    public boolean deleteById(Integer id) {
        return this.questionDao.deleteById(id) > 0;
    }
}
