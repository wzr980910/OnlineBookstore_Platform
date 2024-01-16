package com.platform.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName book
 */
@TableName(value ="book")
@Data
public class Book implements Serializable {
    private Long id;

    private String bookName;

    private String author;

    private Long publishId;

    private String publishDate;

    private Long price;

    private String picture;

    private String content;

    private Integer typeId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}