package com.platform.common;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/25/19:07
 * @Description:图书种类等级
 */
public enum BookTypeEnum {

    //一级图书
    FIRST(0),

    //二级图书
    SECOND(1);

    private Integer code;

    BookTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}
