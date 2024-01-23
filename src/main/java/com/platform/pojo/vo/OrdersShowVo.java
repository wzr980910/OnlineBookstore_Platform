package com.platform.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class OrdersShowVo {
    private Long id;

    private Long orderId;

    private String accountNumber;

    private Integer totalPrice;

    private String status;

    private String userName;

    private String phone;

    @ApiModelProperty(value = "合并后的地址")
    private String address;

    private String province;

    private String city;

    private String county;

    private String detail;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    //起始时间
    private Date startingTime;

    //截止时间
    private Date deadline;

    @ApiModelProperty(value="分页查找时的页数")
    private Integer pageNum = 1;

    @ApiModelProperty(value="分页查找时的页容量")
    private Integer pageSize = 10;
}
