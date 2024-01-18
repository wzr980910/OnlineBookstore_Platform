package com.platform.common;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/18/10:44
 * @Description:管理员权限集合类
 */
public enum Authority {
    /*超级管理员*/
    PRIMARY(0),
    /*普通管理员*/
    ORDINARY(1);


    private Integer code;

    Authority(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}
