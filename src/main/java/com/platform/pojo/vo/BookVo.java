package com.platform.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@ApiModel(value="图书Vo对象",description = "图书Vo对象")
public class BookVo {
    @ApiModelProperty(value="图书id")
    @TableId(value = "id",type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value="图书ISBN号")
    private String isbn;

    @ApiModelProperty(value="书名")
    private String bookName;

    @ApiModelProperty(value="作者")
    private String author;

    @ApiModelProperty(value="出版社id")
    private Long publishId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="出版日期")
    private String publishDate;

    @ApiModelProperty(value="图书价格")
    private BigDecimal price;

    @ApiModelProperty(value="查找时的价格下限")
    private BigDecimal priceLow = BigDecimal.ZERO;

    @ApiModelProperty(value="找时的价格上限")
    private BigDecimal priceHigh;

    @ApiModelProperty(value="图书封面(url)格式")
    private String picture;

    @ApiModelProperty(value="图书简介")
    private String content;

    @ApiModelProperty(value="类型Id")
    private Long typeId;

    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    @ApiModelProperty(value="图书状态")
    private Integer isDeleted;

    @ApiModelProperty(value="分页查找时的页数")
    private Integer current;

    @ApiModelProperty(value="分页查找时的页容量")
    private Integer size;

    @ApiModelProperty(value="出版社名称")
    private String publishName;

    @ApiModelProperty(value="图书类型/标签名,在表中字段为type")
    private String category;

}
