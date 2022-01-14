package com.yun.controller;

import com.yun.entity.Question;
import com.yun.entity.ReturnBean;
import com.yun.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 问题表(Question)表控制层
 *
 * @author makejava
 * @since 2022-01-12 17:08:21
 */
@RestController
@RequestMapping("question")
public class QuestionController extends BaseController{
    /**
     * 服务对象
     */
    @Resource
    private QuestionService questionService;

    /**
     * 分页查询
     *
     * @param question 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Question>> queryByPage(Question question, PageRequest pageRequest) {
        return ResponseEntity.ok(this.questionService.queryByPage(question, pageRequest));
    }

    @GetMapping("findAll")
    public ResponseEntity<List<Question>> queryAll() {
        return ResponseEntity.ok(this.questionService.queryAll());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Question> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.questionService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param question 实体
     * @return 新增结果
     */
    @PostMapping("insert")
    public ReturnBean insert( Question question) {
        Question insert = this.questionService.insert(question);
        if (insert!=null) {
            return super.success(question);
        } else {
            return super.fail(question);
        }
    }

    /**
     * 编辑数据
     *
     * @param question 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ReturnBean update( @RequestBody Question question) {
        question.setUpdateTime(new Date());
        Question update = this.questionService.update(question);
        if (update!=null) {
            return super.success(question);
        } else {
            return super.fail(question);
        }
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete")
    public ReturnBean delete(int id) {
        boolean delete = this.questionService.deleteById(id);
        if (delete) {
            return super.success(null);
        } else {
            return super.fail(null);
        }
    }

}

