package com.platform.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName admin
 */
@TableName(value ="admin")
@Data
public class Admin implements Serializable {
    private Integer id;

    private String adminName;

    private String password;

    private Integer level;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}