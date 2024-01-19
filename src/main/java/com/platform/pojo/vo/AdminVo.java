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
 * @Date: 2024/01/18/9:19
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="管理员Vo对象",description = "管理员Vo对象")
public class AdminVo {
    //使用ApiModelProperty来标注字段属性
    @ApiModelProperty(value = "ID",required = false,example = "1")
    private Long id;

    @ApiModelProperty(value = "管理员账号",required = true,example = "root")
    private String adminName;

    @ApiModelProperty(value = "管理员密码",required = true,example = "1234")
    private String password;

    @ApiModelProperty(value = "管理员权限",required = true,example = "1")
    private Integer authority;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "删除状态",required = false,example = "0")
    private Integer isDeleted;

    private Integer pageNum = 1;

    private Integer pageSize = 10;

}
