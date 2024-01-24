package com.platform.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName admin
 */
@TableName(value ="admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="管理员对象",description = "管理员对象")
public class Admin implements Serializable {

    @ApiModelProperty(value = "ID",required = false,example = "1")
    private Long id;

    @ApiModelProperty(value = "管理员账号",required = true,example = "root")
    private String adminName;

    @ApiModelProperty(value = "管理员密码",required = true,example = "1234")
    private String password;

    @ApiModelProperty(value = "管理员权限",required = true,example = "1")
    private Integer authority;

    @ApiModelProperty(value = "创建时间",required = false,example = "2024-01-18")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间",required = false,example = "2024-01-18")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "删除状态",required = false,example = "0")
    private Integer isDeleted;
    @ApiModelProperty(value = "管理员头像",required = false,example = "https://...")
    private String picture;

    private static final long serialVersionUID = 1L;
}