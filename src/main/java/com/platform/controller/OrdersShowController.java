package com.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.respojo.OrdersDetails;
import com.platform.pojo.vo.OrdersShowVo;
import com.platform.service.AddressService;
import com.platform.service.OrdersShowService;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.platform.util.result.ResultCode.OPERATION_FAILURE;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/23/11:46
 * @Description:
 */
@RestController
@RequestMapping("/order")
public class OrdersShowController {
    private OrdersShowService ordersShowService;

    private AddressService addressService;
    @Autowired
    private void setOrdersShowController(OrdersShowService ordersShowService){this.ordersShowService = ordersShowService;}

    @Autowired
    private void setOrdersShowController(AddressService addressService){this.addressService = addressService;}

    /**
     * 订单查询
     * 首页和详情页(上半部分)
     */

    @PostMapping("/selectOrders")
    public RestResult selectOrders(@RequestBody OrdersShowVo ordersShowVo){
        IPage<OrdersShowVo> page = ordersShowService.selectOrders(ordersShowVo);
        if (page != null) {
            //查询成功，包装数据返回
            Map<String, Object> pageInfoMap = new HashMap<>();
            pageInfoMap.put("pageInfo", page);
            return RestResult.success(ResultCode.SUCCESS, "查询成功", pageInfoMap);
        } else {
            //查询失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    /*编辑(更新电话和地址)*/
    @PostMapping("/updateOrder")
    public RestResult updateOrder(@RequestBody OrdersShowVo ordersShowVo){
        //通过订单id获取收货地址id
        Long addressId = ordersShowService.getAddressIdById(ordersShowVo);
        //根据收货地址id更新联系电话和收货地址
        boolean isUpdate = addressService.updateInfo(ordersShowVo);
        if (isUpdate) {
            //更新成功
            return RestResult.success();
        } else {
            //更新失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    /*删除订单*/
    @PostMapping("/removeOrder")
    public RestResult removeOrder(@RequestParam Long id){
        //删除订单
        boolean isDeleted = ordersShowService.removeOrder(id);
        if (isDeleted){
            return RestResult.success();
        } else {
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    /*发货*/
    @PostMapping("/sendGoods")
    public RestResult sendGoods(@RequestParam Long id){
        //删除订单
        boolean isSend = ordersShowService.sendGoods(id);
        if (isSend){
            return RestResult.success();
        } else {
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    /*查看订单详情(下半部分)*/
    @PostMapping("/underDetails")
    public RestResult underDetails(@RequestParam Long orderId){
        //查询并返回数据
        OrdersDetails ordersDetails = ordersShowService.getDetailsById(orderId);
        if (ordersDetails != null) {
            //查询成功
            return RestResult.success(ordersDetails);
        } else {
            //查询失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

}
