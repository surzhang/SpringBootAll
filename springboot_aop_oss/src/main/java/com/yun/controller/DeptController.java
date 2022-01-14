package com.yun.controller;

import com.yun.entity.Dept;
import com.yun.entity.ReturnBean;
import com.yun.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 部门表(Dept)表控制层
 *
 * @author makejava
 * @since 2021-12-13 14:17:36
 */
@Api("部门业务")
@RestController
@RequestMapping("dept")
public class DeptController extends BaseController{
    /**
     * 服务对象
     */
    @Resource
    private DeptService deptService;


    @RequestMapping("hello")
    public Object hello() {
        return new String("how are you");
    }

    /**
     * 通过主键查询单条数据
     * required默认为true，可解决前后端参数不匹配的问题
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("queryById")
    public Dept queryById(@RequestParam(value = "deptNo", required = false, defaultValue = "102") Integer id) {
        return this.deptService.queryById(id);
    }

    /**
     * @ author: zyk
     * @ description:请求路径参数
     * @ date: 2021/12/13 16:38
     * @ param: [id]
     * @ return: com.yun.entity.Dept
     */
    //mybaits中把0相当于空字符串，所以sql语句中要注意
    @GetMapping("queryById/{id}")
    public Dept queryById2(@PathVariable("id") Integer id) {
        return this.deptService.queryById(id);
    }

    @RequestMapping("queryAll")
    public ReturnBean queryAll() {
        List<Dept> list=deptService.queryAll();
        if (list.size()!=0){
            return super.success(list);
        }else{
            return super.fail(list);
        }

    }

    /**
     * 新增数据
     *1.参数放在url中
     * @param dept 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ReturnBean add(Dept dept) {
       boolean b=this.deptService.insert(dept);
        if (b) {
            return super.success(dept);
        } else {
            return super.fail(dept);
        }
    }

    /**
     * @ author: zyk
     * @ description:layui插入校验
     * @ date: 2021/12/15 10:56
     * @ param: [dept, bindingResult]
     * @ return: com.yun.entity.ReturnBean
     */
    @ApiOperation("保存json类型的dept")
    @PostMapping("insertDept")
    public ReturnBean insert(@ApiParam("json格式参数") @RequestBody Dept dept, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            String msg = bindingResult.getFieldError().getDefaultMessage();
            return super.fail(msg);
        }
        dept.setCreateTime(new Date());
        boolean save = this.deptService.insert(dept);
        if (save) {
            return super.success(dept);
        } else {
            return super.fail(dept);
        }
    }

    /**
     * 修改数据
     * @param dept 实体对象
     * @return 修改结果
     */
    @PutMapping("updateDept")
    public ReturnBean update(@RequestBody Dept dept) {
        boolean update = this.deptService.update(dept);
        if (update) {
            return super.success(dept);
        } else {
            return super.fail(dept);
        }
    }
    //接收json数据
    @PostMapping("saveDept")
    public ReturnBean saveDept( @RequestBody Dept dept) {
        boolean saveDept =  deptService.insert(dept);
        if (saveDept) {
           return super.success(dept);
        } else {
           return super.fail(dept);
        }
    }
    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping("deleteDept")
    public ReturnBean delete(@RequestParam(value = "idList[]", required = false) List<Long> idList) {
        boolean removeByIds = this.deptService.deleteBatch(idList);
        if (removeByIds) {
            return super.success(null);
        } else {
            return super.fail(null);
        }
    }

    //token请求放到header中，也是使用json字符串
    @PostMapping("saveDeptHeader")
    public ReturnBean saveDeptHeader(@RequestBody Dept dept,@RequestHeader("token") String token) {
        System.out.println("token："+token);
        boolean saveDept = deptService.insert(dept);
        ReturnBean returnBean = new ReturnBean();
        if (saveDept) {
            returnBean.setCode(0);
            returnBean.setMsg("操作成功");
            returnBean.setData(dept);
        } else {
            returnBean.setCode(1);
            returnBean.setMsg("操作失败");
            returnBean.setData(dept);
        }
        return returnBean;
    }



    //参数放到cookie中
    @PostMapping("saveDeptCookie")
    public ReturnBean saveDeptCookie(@RequestBody Dept dept,@CookieValue(name = "username")String name) {
        System.out.println("cookie："+name);
        boolean saveDept = deptService.insert(dept);
        ReturnBean returnBean = new ReturnBean();
        if (saveDept) {
            returnBean.setCode(0);
            returnBean.setMsg("操作成功");
            returnBean.setData(dept);
        } else {
            returnBean.setCode(1);
            returnBean.setMsg("操作失败");
            returnBean.setData(dept);
        }
        return returnBean;
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete")
    public boolean deleteById(Integer id) {
        return this.deptService.deleteById(id);
    }

}

