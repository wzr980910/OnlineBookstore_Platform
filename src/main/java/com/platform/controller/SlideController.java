package com.platform.controller;


import com.platform.pojo.Slide;
import com.platform.service.SlideService;
import com.platform.util.AliOssUtil;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static com.platform.util.result.ResultCode.*;


/**
 * @author wzr
 * @date 2024/01/22 11:29
 */
@RestController
@RequestMapping("/slide")
@Api(value = "轮播图接口", tags = "轮播图相关的接口", description = "轮播图测试接口")
public class SlideController {

    private AliOssUtil aliOssUtil;
    private SlideService slideService;
    @Autowired
    public void setSlideService(SlideService slideService){this.slideService = slideService;}
    @Autowired
    public void setAliOssUtil(AliOssUtil aliOssUtil) {
        this.aliOssUtil = aliOssUtil;
    }


    @ApiOperation(value = "新增轮播图数据", notes = "file 必填")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/addSlide")
    public RestResult addSlide(@RequestBody Slide slide) {
        Integer rows = slideService.addSlide(slide);
        if (rows > 0) {
            return RestResult.success(ResultCode.SUCCESS, "添加成功", rows);
        } else {
            return RestResult.failure(ResultCode.OPERATION_FAILURE, "添加失败");
        }
    }


    @ApiOperation(value = "更新轮播图", notes = "id 必填")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/updateSlide")
    public RestResult updateSlide(@RequestBody Slide slide) {
        Integer rows = slideService.updateSlide(slide);
        if (rows > 0) {
            return RestResult.success(ResultCode.SUCCESS, "更新成功", rows);
        } else {
            return RestResult.failure(ResultCode.OPERATION_FAILURE, "更新失败");
        }
    }


    @ApiOperation(value = "删除轮播图", notes = "可以删除一个，也可以删除多个")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/deleteSlide")
    public RestResult deleteSlide(@RequestBody ArrayList<Long> slideIdsList) {
        if (slideIdsList == null) return RestResult.failure(PARAM_IS_BLANK);
        Integer rows = slideService.deleteSlide(slideIdsList);
        if (rows > 0) {
            return RestResult.success(ResultCode.SUCCESS, "删除成功", rows);
        } else {
            return RestResult.failure(ResultCode.OPERATION_FAILURE, "删除失败");
        }
    }


    @ApiOperation(value = "上架轮播图", notes = "可以上架一个，也可以上架多个")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/listSlide")
    public RestResult listSlide(@RequestBody ArrayList<Long> slideIdsList) {
        if (slideIdsList == null) return RestResult.failure(PARAM_IS_BLANK);
        Integer rows = slideService.listSlide(slideIdsList);
        if (rows > 0) {
            return RestResult.success(ResultCode.SUCCESS, "上架成功", rows);
        } else {
            return RestResult.failure(ResultCode.OPERATION_FAILURE, "上架失败");
        }
    }


    @ApiOperation(value = "分页查询轮播图", notes = "分页查询")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @GetMapping("/queryAllSlide")
    public RestResult queryAllSlide(@RequestParam(defaultValue = "0") Integer current,
                                    @RequestParam(defaultValue = "10") Integer size) {
        Map<String, Object> map = slideService.querySlidePage(current, size);
        Integer slideTotal = slideService.selectTotal();
        map.put("total",slideTotal);
        if (map != null) {
            return RestResult.success(ResultCode.SUCCESS, "查询成功", map);
        } else {
            return RestResult.failure(ResultCode.OPERATION_FAILURE, "查询失败");
        }
    }


    @ApiOperation(value = "将轮播图上传到oss服务器", notes = "上传图片")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/uploadSlideImg")
    public RestResult uploadSlideImg(@RequestParam(value = "file") MultipartFile file){
        String basePath = "slidePicture/";
        String url="";
        try {
            url = aliOssUtil.upload(file.getBytes(), file, basePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (url != null){
            return RestResult.success(SUCCESS,"上传成功",url);
        }else {
            return RestResult.failure(OPERATION_FAILURE,"添加轮播图失败");
        }
    }

}
