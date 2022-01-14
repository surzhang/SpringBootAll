package com.yun.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * (Upload)实体类
 *
 * @author makejava
 * @since 2021-12-20 14:35:17
 */
@ToString
@Data
public class Upload implements Serializable {
    private static final long serialVersionUID = -51862474746180758L;
    /**
     * 即文件ID，为方便识别命名为表名_id
     */
    private Integer uploadId;
    /**
     * 原文件名
     */
    private String sourceName;
    /**
     * 服务器文件名
     */
    private String destinationName;



}

