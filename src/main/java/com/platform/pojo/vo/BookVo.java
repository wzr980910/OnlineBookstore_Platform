package com.platform.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/16/15:05
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookVo {
    private Long id;

    private String bookName;

    private String ISBN;

    private String author;

    private Long publishId;

    private String publishDate;

    private BigDecimal price;

    private BigDecimal priceLow = BigDecimal.ZERO;

    private BigDecimal priceHigh;

    private String picture;

    private String content;

    private Long typeId;

    private Date createTime;

    private Date updateTime;

    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String publishName;

    private String type;
}
