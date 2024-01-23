package com.platform.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.OrdersShow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.respojo.OrdersDetails;
import com.platform.pojo.vo.BookVo;
import com.platform.pojo.vo.OrdersShowVo;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
* @author 邓桂材
* @description 针对表【orders_show(订单详情表)】的数据库操作Mapper
* @createDate 2024-01-14 16:56:54
* @Entity com.bookStore.pojo.OrdersShow
*/
@Mapper
public interface OrdersShowMapper extends BaseMapper<OrdersShow> {
    /**
     * 查询Orders数据
     */
    IPage<OrdersShowVo> selectOrders(IPage<?> page, OrdersShowVo ordersShowVo);

    /**
     * 查询addressId
     */
    OrdersShow getAddressIdById(OrdersShowVo ordersShowVo);

    /**
     * 修改删除状态
     */
    void removeOrder(Long id, Integer status);

    /**
     * 根据id获取状态
     */
    OrdersShow getStatusById(Long id);
    /**
     * 修改发货状态
     */
    void sendGoods(Long id, Integer status);

    OrdersDetails getDetailsById(Long orderId);

}




