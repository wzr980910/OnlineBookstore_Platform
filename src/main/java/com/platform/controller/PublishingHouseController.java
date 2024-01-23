package com.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.PublishingHouse;
import com.platform.pojo.vo.BookVo;
import com.platform.pojo.vo.PublishingHouseVo;
import com.platform.service.PublishingHouseService;
import com.platform.service.TypeService;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.platform.util.result.ResultCode.BOOK_HAS_EXISTED;
import static com.platform.util.result.ResultCode.OPERATION_FAILURE;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/18/11:06
 * @Description:出版社操作类
 */

@RestController
@Api(value = "/publish",tags = "出版社操作")
@RequestMapping("/publish")
public class PublishingHouseController {

    @Autowired
    private PublishingHouseService publishingHouseService;

    //添加出版社
    @PostMapping("/addPublish")
    public RestResult addPublish(@RequestBody PublishingHouse publishingHouse) {
        //将publishingHouse传入service层进行处理
        boolean isAdd = publishingHouseService.addPublish(publishingHouse);
        if (isAdd) {
            //添加成功,返回成功消息
            return RestResult.success();
        } else {
            //添加失败
            return RestResult.failure(BOOK_HAS_EXISTED);
        }
    }

    //(批量)删除出版社
    @PostMapping("/removePublish")
    public RestResult removePublishsById(@RequestBody List<PublishingHouse> publishingHouses) {
        //删除出版社
        boolean isDeleted = publishingHouseService.removePublishsById(publishingHouses);
        if (isDeleted) {
            //删除成功
            return RestResult.success();
        } else {
            //删除失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    //(批量)登记出版社
    @PostMapping("/listPublishs")
    public RestResult listPublishsById(@RequestBody List<PublishingHouse> publishingHouse) {
        //删除出版社
        boolean isDeleted = publishingHouseService.listPublishsById(publishingHouse);
        if (isDeleted) {
            //删除成功
            return RestResult.success();
        } else {
            //删除失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    //修改出版社
    @PostMapping("/updatePublish")
    public RestResult updatePublish(@RequestBody PublishingHouse publishingHouse) {
        RestResult restResult = null;
        //将publishingHouse传入service层进行处理
        boolean isUpdate = publishingHouseService.updatePublish(publishingHouse);
        if (isUpdate) {
            //修改成功
            restResult = RestResult.success();
        } else {
            //修改失败
            restResult = RestResult.failure(OPERATION_FAILURE);
        }
        return restResult;
    }

    //根据条件查询出版社
    @PostMapping("/selectPublish")
    public RestResult selectPublish(@RequestBody PublishingHouseVo publishingHouseVo) {
        IPage<PublishingHouseVo> page = publishingHouseService.selectPublish(publishingHouseVo);
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

}