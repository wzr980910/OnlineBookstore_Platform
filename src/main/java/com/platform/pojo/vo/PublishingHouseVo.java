package com.platform.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Date: 2024/01/18/11:14
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="出版社Vo对象",description = "出版社Vo对象")
public class PublishingHouseVo {

    @ApiModelProperty(value="出版社id")
    private Long id;

    @ApiModelProperty(value="出版社名称")
    private String publishName;

    @ApiModelProperty(value="出版社地址")
    private String publishAddress;

    @ApiModelProperty(value="出版社联系电话")
    private Long publishPhone;

    @ApiModelProperty(value="出版社邮箱")
    private String publishEmail;

    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    @ApiModelProperty(value="分页查找时的页数")
    private Integer current;

    @ApiModelProperty(value="分页查找时的页容量")
    private Integer size;

    @ApiModelProperty(value="图书状态")
    private Integer isDeleted;

    @ApiModelProperty(value="出版社数量")
    private Integer total;
}
