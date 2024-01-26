package com.platform.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="库存Vo对象",description = "库存Vo对象")
public class StockVo {

    @ApiModelProperty(value="id")
    private Long id;

    @ApiModelProperty(value="图书id")
    private Long bookId;

    @ApiModelProperty(value="图书isbn")
    private String isbn;

    @ApiModelProperty(value="库存数量")
    private Integer stockNum;

    @ApiModelProperty(value="图书名称")
    private String bookName;

    @ApiModelProperty(value="图书封面")
    private String picture;

    @ApiModelProperty(value="创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value="更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value="图书状态")
    private Integer isDeleted;

    @ApiModelProperty(value="分页查找时的页数")
    private Integer current;

    @ApiModelProperty(value="分页查找时的页容量")
    private Integer size;

    @ApiModelProperty(value="库存数量")
    private Integer total;

    @ApiModelProperty(value="出版社名称")
    private String publishName;
}
