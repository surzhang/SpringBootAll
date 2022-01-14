package com.yun.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.yun.dao.UploadDao;
import com.yun.entity.Upload;
import com.yun.service.UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * (Upload)表服务实现类
 *
 * @author makejava
 * @since 2021-12-20 14:35:17
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService {
    @Resource
    private UploadDao uploadDao;

    @Value("${aliyun.oss.endPoint}")
    public String endpoint;

    @Value("${aliyun.oss.accesskeyId}")
    public String accessKeyId;

    @Value("${aliyun.oss.accesskeySecret}")
    public String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    public String BUCKET_NAME;

    /**
     * 通过ID查询单条数据
     *
     * @param uploadId 主键
     * @return 实例对象
     */
    @Override
    public Upload queryById(Integer uploadId) {
        return this.uploadDao.queryById(uploadId);
    }

    @Override
    public List<Upload> queryAll() {
        return uploadDao.queryAll();
    }

    @Override
    public Upload upload(MultipartFile file) {
        Upload fileupload = new Upload();
        //原文件名
        String oldName=file.getOriginalFilename();
        UUID uuid=UUID.randomUUID();
        String newName = uuid+oldName;
        //文件上传到oss
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 创建PutObjectRequest对象。
        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        PutObjectRequest putObjectRequest = null;
        try {
            putObjectRequest = new PutObjectRequest(BUCKET_NAME, newName, new ByteArrayInputStream(file.getBytes()));
            // 上传字符串。
            ossClient.putObject(putObjectRequest);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        fileupload.setSourceName(file.getOriginalFilename());
        fileupload.setDestinationName("https://exampledemo.oss-cn-beijing.aliyuncs.com/"+newName);
        return fileupload;
    }

    /**
     * 新增数据
     *
     * @param upload 实例对象
     * @return 实例对象
     */
    @Override
    public Upload insert(Upload upload) {
        this.uploadDao.insert(upload);
        return upload;
    }

    /**
     * 修改数据
     *
     * @param upload 实例对象
     * @return 实例对象
     */
    @Override
    public Upload update(Upload upload) {
        this.uploadDao.update(upload);
        return this.queryById(upload.getUploadId());
    }

    /**
     * 通过主键删除数据
     *
     * @param uploadId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer uploadId) {
        return this.uploadDao.deleteById(uploadId) > 0;
    }
}
