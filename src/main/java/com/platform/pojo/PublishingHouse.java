package com.platform.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName publishing_house
 */
@TableName(value ="publishing_house")
@Data
public class PublishingHouse implements Serializable {
    private Long id;

    private String publishName;

    private String publishAddress;

    private Long publishPhone;

    private String publishEmail;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}