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
 * @Date: 2024/01/23/15:38
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="订单Vo对象",description = "订单Vo对象")
public class OrdersShowVo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "用户账号")
    private String accountNumber;

    @ApiModelProperty(value="订单总价")
    private Integer totalPrice;

    @ApiModelProperty(value="订单状态")
    private String status;

    @ApiModelProperty(value="用户名")
    private String userName;

    @ApiModelProperty(value="联系电话")
    private String phone;

    @ApiModelProperty(value = "后端修改地址时使用的addressId")
    private Long addressId;

    @ApiModelProperty(value = "合并后的地址")
    private String address;

    @ApiModelProperty(value="省")
    private String province;

    @ApiModelProperty(value="城市")
    private String city;

    @ApiModelProperty(value="乡镇")
    private String county;

    @ApiModelProperty(value="详细地址")
    private String detail;

    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    @ApiModelProperty(value="起始时间")
    private Date startingTime;

    @ApiModelProperty(value="截止时间")
    private Date deadline;

    @ApiModelProperty(value="分页查找时的页数")
    private Integer current;

    @ApiModelProperty(value="分页查找时的页容量")
    private Integer size;

    @ApiModelProperty(value="订单数量")
    private Integer total;
}
