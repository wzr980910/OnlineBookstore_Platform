package com.platform.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/18/9:20
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="用户Vo对象",description = "用户Vo对象")
public class UserVo {

    @ApiModelProperty(value="用户id")
    private Long id;

    @ApiModelProperty(value="用户账号")
    private String accountNumber;

    @ApiModelProperty(value="用户密码")
    private String password;

    @ApiModelProperty(value="用户名")
    private String username;

    @ApiModelProperty(value="用户手机号")
    private String phoneNumber;

    @ApiModelProperty(value="用户头像")
    private String picture;

    @ApiModelProperty(value="用户性别")
    private Long gender;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除状态",required = false,example = "0")
    private Integer isDeleted;

    @ApiModelProperty(value="分页查找时的页数")
    private Integer current;

    @ApiModelProperty(value="分页查找时的页容量")
    private Integer size;

    @ApiModelProperty(value="用户数量")
    private Integer total;
}
