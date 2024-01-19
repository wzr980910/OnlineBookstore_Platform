package com.platform.controller;

import com.platform.pojo.PublishingHouse;
import com.platform.pojo.vo.PublishingHouseVo;
import com.platform.service.PublishingHouseService;
import com.platform.service.TypeService;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public RestResult addPublish(@RequestBody PublishingHouse publishingHouse){
        RestResult restResult = null;
        //将publishingHouse传入service层进行处理
        boolean isAdd = publishingHouseService.addPublish(publishingHouse);
        if(isAdd){
            //添加成功,返回成功消息
            restResult = RestResult.success();
        }else{
            //添加失败
            restResult = RestResult.failure(BOOK_HAS_EXISTED);
        }
        return restResult;
    }

    //删除出版社
    @PostMapping("/deldetePublish")
    public RestResult deldetePublish(@RequestParam long id){
        RestResult restResult = null;
        //删除图书
        boolean isDeleted = publishingHouseService.deleteById(id);
        if (isDeleted){
            //删除成功
            restResult =  RestResult.success();
        }else {
            //删除失败
            restResult = RestResult.failure(OPERATION_FAILURE);
        }
        return restResult;
    }

    //修改出版社
    @PostMapping("/updatePublish")
    public RestResult updatePublish(@RequestBody PublishingHouse publishingHouse){
        RestResult restResult = null;
        //将bookVo传入service层进行处理
        boolean isUpdate = publishingHouseService.updatePublish(publishingHouse);
        if (isUpdate){
            //修改成功
            restResult =  RestResult.success();
        }else {
            //修改失败
            restResult = RestResult.failure(OPERATION_FAILURE);
        }
        return restResult;
    }

    //根据条件查询出版社
    @PostMapping("/selectPublish")
    public RestResult selectPublish(@RequestBody PublishingHouseVo PublishingHouseVo){
        RestResult restResult=null;
        Map<String,Object> map=publishingHouseService.selectPublish(PublishingHouseVo);
        //查询成功，包装数据返回
        restResult=new RestResult(ResultCode.SUCCESS,map);
        return restResult;
    }
}
