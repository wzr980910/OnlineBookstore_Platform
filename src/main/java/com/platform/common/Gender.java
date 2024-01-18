package com.platform.common;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/18/10:40
 * @Description:性别集合类
 */
public enum Gender {
    /*性别男*/
    MALE(1),
    /*性别女*/
    FEMALE(2);


    private Integer code;

    Gender(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}
