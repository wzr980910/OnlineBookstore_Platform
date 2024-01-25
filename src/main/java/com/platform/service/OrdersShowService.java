package com.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.pojo.OrdersShow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.respojo.OrdersDetails;
import com.platform.pojo.vo.OrdersShowVo;

/**
* @author wzr
* @description 针对表【orders_show(订单详情表)】的数据库操作Service
* @createDate 2024-01-14 16:56:54
*/
public interface OrdersShowService extends IService<OrdersShow> {

    /**
     * 根据条件查询订单
     */
    Page<OrdersShowVo> selectOrders(OrdersShowVo ordersShowVo);

    /**
     * 根据订单号查询addressId
     */
    Long getAddressIdById(OrdersShowVo ordersShowVo);

    /**
     * 删除订单
     */
    int removeOrder(Long id);

    /**
     * 发货
     */
    int sendGoods(Long id);

    /**
     * 查询详情
     */
    OrdersDetails getDetailsById(Long orderId);
}
