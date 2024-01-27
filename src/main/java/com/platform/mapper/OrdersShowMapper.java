package com.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.OrdersShow;
import com.platform.pojo.respojo.OrdersDetails;
import com.platform.pojo.vo.OrdersShowVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    IPage<OrdersShowVo> selectOrders(IPage<?> page,@Param("ordersShowVo") OrdersShowVo ordersShowVo);

    /**
     * 查询addressId
     */
    OrdersShow getAddressIdById(@Param("ordersShowVo")OrdersShowVo ordersShowVo);

    /**
     * 修改删除状态
     */
    int removeOrder(Long orderId, Integer status);

    /**
     * 修改发货状态
     */
    int sendGoods(Long orderId, Integer status);

    /**
     * 查询订单详情
     */
    List<OrdersDetails> getDetailsById(Long orderId);

    /**
     * 查询订单数量
     */
    Integer selectTotal(@Param("ordersShowVo")OrdersShowVo ordersShowVo);

}




