package com.yun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * (Tester)表实体类
 *
 * @author makejava
 * @since 2021-11-25 09:48:49
 */
@SuppressWarnings("serial")
@TableName("tbl_tester")
public class Tester extends Model<Tester> {
    /**
     * 题编号,value代表数据库主键的名字，type代表主键自增
     */
    @TableId(value = "id" ,type = IdType.AUTO)
    private Integer id;
    
    private String phone;
    
    private String username;
    
    private Date testTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }

