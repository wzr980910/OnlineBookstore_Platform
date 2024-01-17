package com.platform.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.platform.common.DefaultAddress;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @TableName address
 */
@TableName(value ="address")
@Data
public class Address implements Serializable {
    private Long id;

    private Long userId;

    private String province;

    private String city;

    private String county = "中国";

    private String detail;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "请输入正确的手机格式")
    private String phone;
    @NotBlank
    private String userName;

    @EnumValue
    private DefaultAddress defaultAddress= DefaultAddress.DEFAULT_ADDRESS;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;



}