package com.yun.controller;

import com.yun.entity.ReturnBean;
import com.yun.entity.Upload;
import com.yun.service.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Upload)表控制层
 *
 * @author makejava
 * @since 2021-12-20 14:35:14
 */
@RestController
@RequestMapping("upload")
public class UploadController {
    /**
     * 服务对象
     */
    @Resource
    private UploadService uploadService;

    @RequestMapping("queryAll")
    public ReturnBean findAll(){
        List<Upload> uploads = uploadService.queryAll();
        ReturnBean returnBean=new ReturnBean();
        returnBean.setData(uploads);
        returnBean.setCode(0);
        returnBean.setMsg("操作成功");
        return returnBean;
    }


    @RequestMapping("insert")
    public ReturnBean insert(MultipartFile file) {
        //第一步，保存文件到OSS
        Upload upload = uploadService.upload(file);
        //第二步保存文件到数据库
        System.out.println(upload.toString());
        Upload insert = uploadService.insert(upload);
        ReturnBean returnBean = new ReturnBean();
        returnBean.setData(insert);
        returnBean.setCode(0);
        returnBean.setMsg("上传成功");
        return returnBean;
    }

    /**
     * 新增数据
     *
     * @param upload 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Upload> add(Upload upload) {
        return ResponseEntity.ok(this.uploadService.insert(upload));
    }

    /**
     * 编辑数据
     *
     * @param upload 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Upload> edit(Upload upload) {
        return ResponseEntity.ok(this.uploadService.update(upload));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.uploadService.deleteById(id));
    }

}

