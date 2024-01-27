package com.platform.pojo.respojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/23/20:04
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDetails {

    private String bookName;

    private String publishName;

    private String type;

    private BigDecimal price;

    private BigDecimal totalPrice;

    @ApiModelProperty(value="图书数量")
    private Integer number;
}
