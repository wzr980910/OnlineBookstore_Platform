package com.platform.controller;

import com.platform.util.AliOssUtil;
import com.platform.util.result.RestResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

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
public class AliOssController {
    //    自动装配阿里云oss工具类
    @Autowired
    private AliOssUtil aliOssUtil;


    @PostMapping("/upload")
    @ApiOperation("图书封面上传")
    public RestResult upload(@RequestParam(value = "file") MultipartFile file) {
        log.info("文件上传：{}", file);
        String basePath = "bookPicture/";
        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取文件名后缀  xxx.png
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称
            String objectName = basePath + UUID.randomUUID().toString() + extension;
            //返回文件请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return RestResult.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败");
            e.printStackTrace();
        }
        return RestResult.failure("文件上传失败");
    }

    @PostMapping("/imgUpload")
    @ApiOperation("头像上传")
    public RestResult imgUpload(@RequestParam(value = "file") MultipartFile file) {
        log.info("文件上传：{}", file);
        String basePath = "userPicture/";
        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取文件名后缀  xxx.png
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称
            String objectName = basePath + UUID.randomUUID().toString() + extension;
            //返回文件请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return RestResult.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败");
            e.printStackTrace();
        }
        return RestResult.failure("文件上传失败");
    }
}
