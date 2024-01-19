package com.platform.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/18/11:14
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishingHouseVo {

    private Long id;

    private String publishName;

    private String publishAddress;

    private Long publishPhone;

    private String publishEmail;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private Integer isDeleted;
}
