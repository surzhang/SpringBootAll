package com.yun.dao;

import com.yun.entity.Upload;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Upload)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-20 14:35:16
 */

public interface UploadDao {

    /**
     * 通过ID查询单条数据
     *
     * @param uploadId 主键
     * @return 实例对象
     */
    Upload queryById(Integer uploadId);

    List<Upload>queryAll();
    /**
     * 统计总行数
     *
     * @param upload 查询条件
     * @return 总行数
     */
    long count(Upload upload);

    /**
     * 新增数据
     *
     * @param upload 实例对象
     * @return 影响行数
     */
    int insert(Upload upload);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Upload> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Upload> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Upload> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Upload> entities);

    /**
     * 修改数据
     *
     * @param upload 实例对象
     * @return 影响行数
     */
    int update(Upload upload);

    /**
     * 通过主键删除数据
     *
     * @param uploadId 主键
     * @return 影响行数
     */
    int deleteById(Integer uploadId);

}

