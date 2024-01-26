package com.platform.config;

import com.platform.util.result.RestResult;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.List;

import static com.platform.util.result.ResultCode.PARAM_IS_INVALID;

/*
*   全局异常处理
*/
@ControllerAdvice
@Configuration
public class GlobalExceptionHandler {

    //处理参数校验异常

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResult handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder errorInfo=new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            // 获取校验失败的字段名
            String fieldName = fieldError.getField();
            // 获取校验失败的提示信息
            String errorMessage = fieldError.getDefaultMessage();
            // 拼接错误信息
            errorInfo.append(fieldName).append(": ").append(errorMessage).append("; ");
        }
        RestResult restResult = new RestResult();
        restResult.setMessage(errorInfo.toString());
        return restResult;
    }

    //处理文件超出范围
    @ExceptionHandler(FileSizeLimitExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    @ResponseBody
    public RestResult handleFileSizeLimitExceededException(FileSizeLimitExceededException exc) {
        return RestResult.failure(PARAM_IS_INVALID.getCode(),"图片超出大小限制");
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    @ResponseBody
    public RestResult handleIOException(IOException exc) {
        return RestResult.failure(PARAM_IS_INVALID.getCode(),"图片上传失败");
    }
}