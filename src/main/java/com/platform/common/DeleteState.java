package com.platform.common;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/16/11:02
 * @Description:删除状态码
 */
public enum DeleteState {
    /*未删除*/
    NO_DELETE(1),
    /*已删除*/
    IS_DELETE(2);


    private Integer code;

    DeleteState(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }


}
