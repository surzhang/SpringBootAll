package com.yun.service;

import com.yun.entity.Upload;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (Upload)表服务接口
 *
 * @author makejava
 * @since 2021-12-20 14:35:17
 */
public interface UploadService {

    /**
     * 通过ID查询单条数据
     *
     * @param uploadId 主键
     * @return 实例对象
     */
    Upload queryById(Integer uploadId);

   List<Upload>queryAll();
    /**
     * 新增数据
     *
     * @param upload 实例对象
     * @return 实例对象
     */
    Upload insert(Upload upload);

    /**
     * 修改数据
     *
     * @param upload 实例对象
     * @return 实例对象
     */
    Upload update(Upload upload);

    /**
     * 通过主键删除数据
     *
     * @param uploadId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer uploadId);

    Upload upload(MultipartFile file);
}
