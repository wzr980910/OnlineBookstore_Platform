package com.platform.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    @TableId
    private Long id;

    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{5,19}$",
            message = "账号由字母、数字和下划线组成，以字母开头，长度在6到20个字符之间")
    private String accountNumber;

    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?]{8,20}$"
            ,message = "密码长度在8到20个字符之间，可以包含字母、数字和特殊字符")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$",message = "用户名只包含字母和数字，长度在3到20个字符之间")
    private String username;

    @NotBlank(message="手机号不能为空")
    @Pattern(regexp="^1\\d{10}$", message="请输入正确的手机格式")
    private String phoneNumber;

    private String picture;

    private Long gender;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}