package com.yun.service;

import com.yun.entity.Dept;

import java.util.List;


/**
 * 部门表(Dept)表服务接口
 *
 * @author makejava
 * @since 2021-12-13 14:17:39
 */
public interface DeptService {

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    Dept queryById(Integer deptId);

    List<Dept> queryAll();
    List<Dept>selectAll(Long page  , Long limit);
    /**
     * 新增数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    boolean insert(Dept dept);

    /**
     * 修改数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    boolean update(Dept dept);

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer deptId);

    boolean deleteBatch (List<Long> myList);
}
