package com.yun.dao;

import com.yun.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门表(Dept)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-13 14:17:37
 */
@Mapper
public interface DeptDao {

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
   /* @Select("select * from tbl_dept where dept_id=#{deptId}")
    @ResultMap(value = {"deptMap"})*/
    Dept queryById(Integer deptId);

    /**
     * @ author: zyk
     * @ description:查询所有
     * @ date: 2021/12/13 14:21
     * @ param: []
     * @ return: com.yun.entity.Dept
     */
 /*   @Select("select * from tbl_dept")
        @Results(id="deptMap",value={
            @Result( property = "deptId",column = "dept_id"),
            @Result(property = "deptName",column = "dept_name"),
            @Result(property = "orderNum",column = "order_num"),
            @Result(property = "status",column = "status"),
            @Result(property = "delFlag",column = "del_flag"),
            @Result(property = "createBy",column = "create_by"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateBy",column = "update_by"),
            @Result(property = "updateTime",column = "update_time")
        })*/
    List<Dept> queryAll();

    /**
     * 新增数据
     *
     * @param dept 实例对象
     * @return 影响行数
     */
    //@Insert("insert intotbl_dept(dept_name,order_num) values(#{deptName},#{orderNum})")
    int insert(Dept dept);
    /**
     * 修改数据
     *
     * @param dept 实例对象
     * @return 影响行数
     */
    //@Update(" update tbl_dept set dept_name = #{deptName}, order_num = #{orderNum}  where dept_id = #{deptId}")
    int update(Dept dept);

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 影响行数
     */
    //@Delete("delete from tbl_dept where dept_id = #{deptId}")
    int deleteById(Integer deptId);
    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dept> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Dept> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dept> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Dept> entities);

    /**
     * 统计总行数
     *
     * @param dept 查询条件
     * @return 总行数
     */
    long count(Dept dept);

    boolean deleteBatch (@Param("myList") List<Long> myList);

    List<Dept> selectAllDept(@Param("page")Long page, @Param("limit") Long limit);

}

