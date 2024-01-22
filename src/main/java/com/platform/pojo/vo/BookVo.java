package com.platform.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "图书信息")
public class BookVo {
    @ApiModelProperty(value="图书id")
    private Long id;

    @ApiModelProperty(value="图书ISBN号")
    private String ISBN;

    @ApiModelProperty(value="书名")
    private String bookName;

    @ApiModelProperty(value="作者")
    private String author;

    private Long publishId;

    private String publishDate;

    private BigDecimal price;

    @ApiModelProperty(value="查找时的价格下限")
    private BigDecimal priceLow = BigDecimal.ZERO;

    @ApiModelProperty(value="找时的价格上限")
    private BigDecimal priceHigh;

    private String picture;

    private String content;

    private Long typeId;

    private Date createTime;

    private Date updateTime;

    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;

    @ApiModelProperty(value="分页查找时的页数")
    private Integer pageNum = 1;

    @ApiModelProperty(value="分页查找时的页容量")
    private Integer pageSize = 10;

    @ApiModelProperty(value="出版社名称")
    private String publishName;

    //按照价格排序
    @ApiModelProperty(value="价格排序",example = "0/1/2",notes = "0不按照这一项排序，1升序，2降序")
    private Integer priceOrder;

    @ApiModelProperty(value="图书类型/标签名")
    private String type;
}
