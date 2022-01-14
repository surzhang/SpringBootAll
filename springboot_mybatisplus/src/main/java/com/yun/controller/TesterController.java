package com.yun.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.yun.entity.ReturnBean;
import com.yun.entity.Tester;
import com.yun.service.TesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * (Tester)表控制层
 *
 * @author makejava
 * @since 2021-11-25 09:48:49
 */
@RestController
@RequestMapping("tester")
@Slf4j
public class TesterController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private TesterService testerService;

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param tester 查询实体
     * @return 所有数据
     */
    @GetMapping("selectAll")
    public ReturnBean selectAll(Long page, Long limit, Tester tester) {
        //重新构建分页对象
        Page<Tester> pageObj = new Page<>(page, limit);
        Page<Tester> testerPage = this.testerService.page(pageObj, new QueryWrapper<>(tester));
        return super.success(testerPage.getRecords(),testerPage.getTotal());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ReturnBean selectOne(@PathVariable Serializable id) {
        Tester tester = this.testerService.getById(id);
        return super.success(tester);

    }

    /**
     * 新增数据
     *
     * @param tester 实体对象
     * @return 新增结果
     *
     */
    @PostMapping("insert")
    public ReturnBean insert(@RequestBody Tester tester) {
        tester.setTestTime(new Date());
        boolean save = this.testerService.save(tester);
        if (save) {
            return super.success(tester);
        } else {
            return super.fail(tester);
        }
    }

    /**
     * 修改数据
     * @param tester 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public ReturnBean update(@RequestBody Tester tester) {

        boolean update = this.testerService.updateById(tester);
        if (update) {
            return super.success(tester);
        } else {
            return super.fail(tester);
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
        return super.success(this.testerService.removeByIds(idList));
    }

/*    @RequestMapping("/h")
    public ModelAndView goHome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tester");
        return mv;
    }*/
}

