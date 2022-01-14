package com.yun.controller;

import com.yun.entity.UpOrDown;
import com.yun.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * @ fileName:OssController
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/14 16:13
 * @ version:1.0.0
 */
@RestController
@RequestMapping("oss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    /**
     * @ author: zyk
     * @ description:上传字符串到文件内
     * @ date: 2021/12/14 19:59
     * @ param: [str]
     * @ return: com.yun.entity.ReturnBean
     */
    @PostMapping("uploadStr")
    public UpOrDown uploadStr(@RequestParam(value = "str") String str) {
        boolean b = ossService.uploadString(str);
        if (b) {
            return new UpOrDown(0, "操作成功");
        } else {
            return new UpOrDown(1, "操作失败");
        }
    }

    /**
     * @ author: zyk
     * @ description:上传文件到OSS
     * @ date: 2021/12/14 21:38
     * @ param: [file]
     * @ return: com.yun.entity.UpOrDown
     */
    @PostMapping("uploadFile")
    public UpOrDown uploadFile1(@RequestParam(value = "file") MultipartFile file) {
        //获取文件名
        if (file == null) {
            return new UpOrDown(1, "操作失败");
        }
        File localFile = null;
        try {
            localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            String path = localFile.getAbsolutePath();
            boolean b = ossService.uploadFile(path);
            if (b) {
                return new UpOrDown(0, "操作成功");
            } else {
                return new UpOrDown(1, "操作失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return new UpOrDown(1, "操作失败");
        }
    }

   /**
    * @ author: zyk
    * @ description:下载文件
    * @ date: 2021/12/15 8:46
    * @ param: []
    * @ return: com.yun.entity.UpOrDown
    */
    @GetMapping("downloadFile")
    public UpOrDown downloadFile() {
        boolean b = ossService.downloadFile();
        if (b) {
            return new UpOrDown(0, "操作成功");
        } else {
            return new UpOrDown(1, "操作失败");
        }
    }

}
