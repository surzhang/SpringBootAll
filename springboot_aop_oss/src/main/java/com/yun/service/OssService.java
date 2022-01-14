package com.yun.service;

/**
 * @ fileName:OssService
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/14 16:15
 * @ version:1.0.0
 */
public interface OssService {


   boolean uploadString(String str);
   boolean uploadFile(String path);
   boolean downloadFile();

}
