package com.platform.config;

import com.platform.util.result.RestResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
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
}