package com.platform.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/23/20:34
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomePage {

    private Integer totalUsers;//用户总数

    private Integer totalBooks;//图书总数

    private Integer totalOrders;//订单总数

    private BigDecimal totalIncome;//总收入

    /**
     * 左下角柱状图
     */
    private String type; //图书类型

    private Integer totalSale;//图书销量

    /**
     * 右下角
     */
    private String month;//月份
    private Integer ordersNumber;//订单数量

}
