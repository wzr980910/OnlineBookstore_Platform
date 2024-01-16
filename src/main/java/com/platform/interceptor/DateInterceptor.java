package com.platform.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/15/19:21
 * @Description:日期默认值填充拦截器
 */
@Slf4j
@Component
public class DateInterceptor implements MetaObjectHandler {
    /**新增时间**/
    private final String TIME_CREATE = "createTime";
    /**更新时间**/
    private final String TIME_UPDATE = "updateTime";
    @Override
    public void insertFill(MetaObject metaObject) {

        //添加时间、最后更新时间
        this.strictInsertFill(metaObject, TIME_CREATE, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, TIME_UPDATE, LocalDateTime.class, LocalDateTime.now());

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        //最后更新时间
        this.strictInsertFill(metaObject, TIME_UPDATE, LocalDateTime.class, LocalDateTime.now());

    }
}
