package com.yun.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.yun.entity.Question;
import com.yun.entity.ReturnBean;
import com.yun.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 问题表(Question)表控制层
 *
 * @author
 * @since 2021-11-24 14:57:10
 */
@RestController
@RequestMapping("question")
@Slf4j
public class QuestionController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private QuestionService questionService;

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param question 查询实体
     * @return 所有数据
     */
    @GetMapping("selectAll")
    public ReturnBean selectAll(Long page, Long limit, Question question) {
        //重新构建分页对象
        Page<Question> pageObj = new Page<>(page, limit);
        Page<Question> questionPage = this.questionService.page(pageObj, new QueryWrapper<>(question));
        return super.success(questionPage.getRecords(),questionPage.getTotal());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ReturnBean selectOne(@PathVariable Serializable id) {
        Question question = this.questionService.getById(id);
        return super.success(question);

    }

    /**
     * 新增数据
     *
     * @param question 实体对象
     * @return 新增结果
     *
     */
    @PostMapping("insert")
    public ReturnBean insert(@RequestBody Question question) {
        //log.info(question.toString());
        question.setCreateTime(new Date());
        boolean save = this.questionService.save(question);
        if (save) {
            return super.success(question);
        } else {
            return super.fail(question);
        }
    }

    /**
     * 修改数据
     *
     * @param question 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public ReturnBean update(@RequestBody Question question) {
        question.setUpdateTime(new Date());
        boolean update = this.questionService.updateById(question);
        if (update) {
            return super.success(question);
        } else {
            return super.fail(question);
        }
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
     @DeleteMapping("/delete")
    public ReturnBean delete(@RequestParam("id") List<Long> idList) {
        return super.success(this.questionService.removeByIds(idList));
    }
}

