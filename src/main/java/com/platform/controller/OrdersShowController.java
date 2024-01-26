package com.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.respojo.OrdersDetails;
import com.platform.pojo.vo.OrdersShowVo;
import com.platform.service.AddressService;
import com.platform.service.OrdersShowService;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "订单接口", tags = "订单相关的接口", description = "订单测试接口")
public class OrdersShowController {
    private OrdersShowService ordersShowService;
    private AddressService addressService;
    @Autowired
    private void setOrdersShowService(OrdersShowService ordersShowService){this.ordersShowService = ordersShowService;}
    @Autowired
    private void setAddressService(AddressService addressService){this.addressService = addressService;}

    @ApiOperation(value = "根据条件查询订单", notes = "传参类型为OrdersShowVo实体类,传参为空时查询全部")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/selectOrders")
    public RestResult selectOrders(@RequestBody OrdersShowVo ordersShowVo){
        IPage<OrdersShowVo> page = ordersShowService.selectOrders(ordersShowVo);
        Integer orderTotal = ordersShowService.selectTotal(ordersShowVo);
        if (page != null) {
            //查询成功，包装数据返回
            Map<String, Object> pageInfoMap = new HashMap<>();
            pageInfoMap.put("pageInfo", page);
            pageInfoMap.put("total", orderTotal);
            return RestResult.success(ResultCode.SUCCESS, "查询成功", pageInfoMap);
        } else {
            //查询失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    @ApiOperation(value = "编辑(更新电话和地址)", notes = "传参类型为OrdersShowVo实体类")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/updateOrder")
    public RestResult updateOrder(@RequestBody OrdersShowVo ordersShowVo){
        //通过订单id获取收货地址id
        Long addressId = ordersShowService.getAddressIdById(ordersShowVo);
        ordersShowVo.setAddressId(addressId);
        //根据收货地址id更新联系电话和收货地址
        int row = addressService.updateInfo(ordersShowVo);
        if (row > 0) {
            //更新成功
            return RestResult.success();
        } else {
            //更新失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    @ApiOperation(value = "删除订单", notes = "传参类型为id")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/removeOrder")
    public RestResult removeOrder(@RequestParam Long id){
        //删除订单
        int row = ordersShowService.removeOrder(id);
        if (row > 0){
            return RestResult.success();
        } else {
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    @ApiOperation(value = "发货", notes = "传参类型为id")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/sendGoods")
    public RestResult sendGoods(@RequestParam Long id){
        //发货
        int row  = ordersShowService.sendGoods(id);
        if (row > 0){
            return RestResult.success();
        } else {
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    @ApiOperation(value = "查看订单详情(下半部分)", notes = "传参类型为orderId")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
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
