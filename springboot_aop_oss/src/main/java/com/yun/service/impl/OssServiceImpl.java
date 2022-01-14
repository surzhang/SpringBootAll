package com.yun.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.yun.service.OssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;

/**
 * @ fileName:OssServiceImpl
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/14 16:15
 * @ version:1.0.0
 */

@Service
public class OssServiceImpl implements OssService {

    @Value("${aliyun.oss.endPoint}")
    public String endpoint;

    @Value("${aliyun.oss.accesskeyId}")
    public String accessKeyId;

    @Value("${aliyun.oss.accesskeySecret}")
    public String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    public String BUCKET_NAME;

    /**
     * @ author: zyk
     * @ description:上传字符串
     * @ date: 2021/12/15 9:02
     * @ param: [str]
     * @ return: boolean
     */
    @Override
    public boolean uploadString(String str) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写字符串。
        String content = str;

        // 创建PutObjectRequest对象。
        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, "exampledir/exampleobject.txt", new ByteArrayInputStream(content.getBytes()));
        if (putObjectRequest == null) {
            return false;
        } else {
            // 上传字符串。
            ossClient.putObject(putObjectRequest);

            // 关闭OSSClient。
            ossClient.shutdown();
            return true;
        }
    }

    /**
     * @ author: zyk
     * @ description:上传文件
     * @ date: 2021/12/15 9:02
     * @ param: [path]
     * @ return: boolean
     */
    @Override
    public boolean uploadFile(String path) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        // 依次填写Bucket名称（例如examplebucket）、Object完整路径（例如exampledir/exampleobject.txt）和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, "exampledir/exampleobject2.txt", new File(path));
        if (putObjectRequest == null) {
            return false;
        } else {
            // 上传字符串。
            ossClient.putObject(putObjectRequest);
            // 关闭OSSClient。
            ossClient.shutdown();
            return true;
        }
    }

    @Override
    public boolean downloadFile() {
        // 填写Bucket名称。
        String bucketName = BUCKET_NAME;
        // 填写不包含Bucket名称在内的Object完整路径，例如testfolder/exampleobject.txt。
        String objectName = "exampledir/exampleobject2.txt";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 下载Object到本地文件，并保存到指定的本地路径中。如果指定的本地文件存在会覆盖，不存在则新建。
        // 如果未指定本地路径，则下载后的文件默认保存到示例程序所属项目对应本地路径中。
        ObjectMetadata object = ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File("F:\\x.txt"));
        if (object == null) {
            return false;
        } else {
            // 关闭OSSClient。
            ossClient.shutdown();
            return true;
        }
    }
}


