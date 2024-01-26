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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/publish")
@Api(value = "出版社接口", tags = "出版社相关的接口", description = "出版社测试接口")
public class PublishingHouseController {
    private PublishingHouseService publishingHouseService;
    @Autowired
    public void setPublishingHouseService(PublishingHouseService publishingHouseService){
        this.publishingHouseService = publishingHouseService;
    }

    @ApiOperation(value = "添加出版社", notes = "添加出版社,此时出版社Id不需要")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/addPublish")
    public RestResult addPublish(@RequestBody PublishingHouse publishingHouse) {
        //将publishingHouse传入service层进行处理
        int row = publishingHouseService.addPublish(publishingHouse);
        if (row > 0) {
            //添加成功,返回成功消息
            return RestResult.success();
        } else {
            //添加失败
            return RestResult.failure(BOOK_HAS_EXISTED);
        }
    }

    @ApiOperation(value = "(批量)删除出版社", notes = "传入参数为List<PublishingHouse>实体类集合")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/removePublish")
    public RestResult removePublishsById(@RequestBody List<PublishingHouse> publishingHouses) {
        //删除出版社
        int row = publishingHouseService.removePublishsById(publishingHouses);
        if (row > 0) {
            //删除成功
            return RestResult.success();
        } else {
            //删除失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    @ApiOperation(value = "(批量)登记出版社", notes = "传入参数为List<PublishingHouse>实体类集合")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
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

    @ApiOperation(value = "修改出版社", notes = "传入参数为PublishingHouse实体类")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
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

    @ApiOperation(value = "根据条件查询出版社", notes = "传入参数为PublishingHouse实体类")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/selectPublish")
    public RestResult selectPublish(@RequestBody PublishingHouseVo publishingHouseVo) {
        IPage<PublishingHouseVo> page = publishingHouseService.selectPublish(publishingHouseVo);
        PublishingHouseVo publishingHouseTotal = publishingHouseService.selectNumber(publishingHouseVo);
        if (page != null) {
            //查询成功，包装数据返回
            Map<String, Object> pageInfoMap = new HashMap<>();
            pageInfoMap.put("pageInfo", page);
            pageInfoMap.put("total", publishingHouseTotal.getTotal());

            return RestResult.success(ResultCode.SUCCESS, "查询成功", pageInfoMap);
        } else {
            //查询失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

}