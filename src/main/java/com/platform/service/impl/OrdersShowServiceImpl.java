package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.OrderStatus;
import com.platform.pojo.OrdersShow;
import com.platform.pojo.respojo.OrdersDetails;
import com.platform.pojo.vo.BookVo;
import com.platform.pojo.vo.OrdersShowVo;
import com.platform.service.OrdersShowService;
import com.platform.mapper.OrdersShowMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void setOrdersShowServiceImpl(OrdersShowMapper ordersShowMapper){this.ordersShowMapper = ordersShowMapper;}

    @Override
    public Page<OrdersShowVo> selectOrders(OrdersShowVo ordersShowVo) {
        //分页
        Page<OrdersShowVo> page = new Page<>(ordersShowVo.getPageNum(), ordersShowVo.getPageSize());
        //查询
        ordersShowMapper.selectOrders(page, ordersShowVo);
        return page;
    }

    @Override
    public Long getAddressIdById(OrdersShowVo ordersShowVo) {
        //通过mapper层查询addressId并返回
        OrdersShow ordersShow = ordersShowMapper.getAddressIdById(ordersShowVo);
        return ordersShow.getAddressId();
    }

    @Override
    public int removeOrder(Long id) {
        //修改删除状态
        Integer status = OrderStatus.IS_DELETED.getCode();
        return ordersShowMapper.removeOrder(id,status);
    }

    @Override
    public int sendGoods(Long id) {
        //修改发货状态
        Integer status = OrderStatus.WAIT_RECEIVE.getCode();
        return ordersShowMapper.sendGoods(id,status);
    }

    @Override
    public OrdersDetails getDetailsById(Long orderId) {
        //通过mapper层查询
        return ordersShowMapper.getDetailsById(orderId);
    }
}




