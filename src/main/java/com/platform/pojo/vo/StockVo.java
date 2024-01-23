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
 * @Date: 2024/01/23/9:11
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockVo {
    private Long id;

    private Long bookId;

    private String ISBN;

    private Integer stockNum;

    private String bookName;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    private Integer isDeleted;

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
