package com.platform.common;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/17/19:14
 * @Description:订单状态表
 */
public enum OrderStatus {
    /**
     * 待付款
     */
    WAIT_PAYMENT(0),
    /**
     * 待发货
     */
    WAIT_SEND(1),
    /**
     * 已发货
     */
    WAIT_RECEIVE(2),
    /**
     * 已收货
     */
    ALREADY_RECEIVE(3),
    /**
     * 注销订单
     */
    IS_DELETED(4);

    private Integer code;

    OrderStatus(Integer code){
        this.code = code;
    }
    public Integer getCode(){
        return this.code;
    }
}
