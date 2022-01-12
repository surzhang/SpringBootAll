package com.yun.springboot_redis.controller;



import com.yun.springboot_redis.entity.Question;
import com.yun.springboot_redis.entity.ReturnBean;
import com.yun.springboot_redis.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 问题表(Question)表控制层
 * @author makejava
 * @since 2021-12-02 14:37:47
 */
@RestController//等于@Controller+@ResponseBody
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
     * @param page     分页对象
     * @param question 查询实体
     * @return 所有数据
     */
    //@RequiresPermissions("character:question:search")
    @RequestMapping("selectAllPage")
    public ReturnBean selectAllPage() {
        List<Question> questions = questionService.queryAll();
        return super.success(questions);
    }


    /**
     * 新增数据
     * @param question 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    public ReturnBean insert( @RequestBody Question question) {
        question.setCreateTime(new Date());
        Question insert = this.questionService.insert(question);
        if (insert!=null) {
            return super.success(question);
        } else {
            return super.fail(question);
        }
    }

    /**
     * 修改数据
     * @param question 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
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
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public ReturnBean delete(int id) {
        boolean delete = this.questionService.deleteById(id);
        if (delete) {
            return super.success(null);
        } else {
            return super.fail(null);
        }
    }
}

