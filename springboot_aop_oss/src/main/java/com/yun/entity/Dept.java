package com.yun.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 部门表(Dept)实体类
 *
 * @author makejava
 * @since 2021-12-13 14:17:38
 */
@ToString
@Data
@Validated
@ApiModel
public class Dept implements Serializable {
    private static final long serialVersionUID = 192445237283875836L;
    /**
     * 部门id
     */
    @ApiModelProperty("部门编号")
    private Integer deptId;
    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    @NotNull(message = "属性不允许为null")
    @Length(min = 3,message = "length的长度必须在3-100之间")
    private String deptName;
    /**
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 部门状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;



}

