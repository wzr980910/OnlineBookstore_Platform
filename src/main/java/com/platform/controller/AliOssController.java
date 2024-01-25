package com.platform.controller;

import com.platform.util.AliOssUtil;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.platform.util.result.ResultCode.OPERATION_FAILURE;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/19/10:34
 * @Description:
 */
@RestController
@RequestMapping("/oss")
@Slf4j
@Api(value = "上传oss存储对象接口", tags = "上传oss", description = "上传oss测试接口")
public class AliOssController {
    //    自动装配阿里云oss工具类
    @Autowired
    private AliOssUtil aliOssUtil;

    @ApiOperation(value = "图书封面上传", notes = "只需要图片文件")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/upload")
    public RestResult upload(@RequestParam(value = "file") MultipartFile file) {
        log.info("文件上传：{}", file);
        String basePath = "bookPicture/";
        try {
            String filePath = aliOssUtil.upload(file.getBytes(), file,basePath);
            return RestResult.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败");
            e.printStackTrace();
        }
        return RestResult.failure(OPERATION_FAILURE.getCode(),"文件上传失败");
    }

    @ApiOperation(value = "头像上传", notes = "只需要头像文件")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/imgUpload")
    public RestResult imgUpload(@RequestParam(value = "file") MultipartFile file) {
        log.info("文件上传：{}", file);
        String basePath = "userPicture/";
        try {
            String filePath = aliOssUtil.upload(file.getBytes(), file,basePath);
            return RestResult.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败");
            e.printStackTrace();
        }
        return RestResult.failure("文件上传失败");
    }
}
