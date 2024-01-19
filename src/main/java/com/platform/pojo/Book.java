package com.platform.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName book
 */
@TableName(value ="book")
@Data
public class Book implements Serializable {

    private Long id;

    private String ISBN;

    private String bookName;

    private String author;

    private Long publishId;

    private String publishDate;

    private BigDecimal price;

    private String picture;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}