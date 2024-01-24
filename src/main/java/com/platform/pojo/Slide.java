package com.platform.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.platform.common.DeleteState;
import com.platform.common.DisplaySlides;
import lombok.Data;

/**
 * @TableName slide
 */
@TableName(value ="slide")
@Data
public class Slide implements Serializable {
    private Long id;

    private String imgUrl;

    /**
     * 默认不展示到前台
     */
    private Integer isDisplay = DisplaySlides.NOT_DISPLAY.getCode();

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;


    private Integer isDelete = DeleteState.NO_DELETE.getCode();


    private static final long serialVersionUID = 1L;
}