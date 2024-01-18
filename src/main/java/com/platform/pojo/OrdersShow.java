package com.platform.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName orders_show
 */
@TableName(value ="orders_show")
@Data
public class OrdersShow implements Serializable {
    private Long id;

    private Long orderId;

    private String accountNumber;

    private Date date;

    private Integer totalPrice;

    private String toShoppingAddress;

    private String contact;

    private String status;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}