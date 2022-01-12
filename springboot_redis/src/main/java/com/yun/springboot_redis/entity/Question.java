package com.yun.springboot_redis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * 问题表(Question)实体类
 *
 * @author makejava
 * @since 2022-01-10 18:56:33
 */
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Question implements Serializable {
    private static final long serialVersionUID = -37568848066100772L;
    /**
     * 问题编号
     */
    private Integer id;
    
    private String question;
    
    private String optionA;
    
    private String optionB;
    
    private String optionC;
    
    private String optionD;
    /**
     * 预留字段
     */
    private Integer type;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 修改人
     */
    private String updater;
    
    private Integer flag;



}

