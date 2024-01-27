package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.OrderStatus;
import com.platform.mapper.OrdersShowMapper;
import com.platform.pojo.OrdersShow;
import com.platform.pojo.respojo.OrdersDetails;
import com.platform.pojo.vo.OrdersShowVo;
import com.platform.service.OrdersShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author wzr
* @description 针对表【orders_show(订单详情表)】的数据库操作Service实现
* @createDate 2024-01-14 16:56:54
*/
@Service
public class OrdersShowServiceImpl extends ServiceImpl<OrdersShowMapper, OrdersShow>
    implements OrdersShowService{

    private OrdersShowMapper ordersShowMapper;
    @Autowired
    public void setOrdersShowMapper(OrdersShowMapper ordersShowMapper){this.ordersShowMapper = ordersShowMapper;}

    /**
     * 条件查询订单
     */
    @Override
    public Page<OrdersShowVo> selectOrders(OrdersShowVo ordersShowVo) {
        //分页
        Page<OrdersShowVo> page = new Page<>(ordersShowVo.getCurrent(), ordersShowVo.getSize());
        //查询
        ordersShowMapper.selectOrders(page, ordersShowVo);
        return page;
    }

    /**
     * 获取地址
     */
    @Override
    public Long getAddressIdById(OrdersShowVo ordersShowVo) {
        //通过mapper层查询addressId并返回
        OrdersShow ordersShow = ordersShowMapper.getAddressIdById(ordersShowVo);
        return ordersShow.getAddressId();
    }

    /**
     * 删除订单
     */
    @Override
    public int removeOrder(Long orderId) {
        //修改删除状态
        Integer status = OrderStatus.IS_DELETED.getCode();
        return ordersShowMapper.removeOrder(orderId,status);
    }

    /**
     * 发货
     */
    @Override
    public int sendGoods(Long orderId) {
        //修改发货状态
        Integer status = OrderStatus.WAIT_RECEIVE.getCode();
        return ordersShowMapper.sendGoods(orderId,status);
    }

    /**
     * 获取订单详情
     */
    @Override
    public List<OrdersDetails> getDetailsById(Long orderId) {
        //通过mapper层查询
        return ordersShowMapper.getDetailsById(orderId);
    }

    /**
     * 查询订单数量
     */
    @Override
    public Integer selectTotal(OrdersShowVo ordersShowVo) {
        return ordersShowMapper.selectTotal(ordersShowVo);
    }
}




