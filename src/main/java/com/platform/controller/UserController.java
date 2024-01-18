package com.platform.controller;


import com.platform.pojo.User;
import com.platform.util.result.RestResult;
import com.platform.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import static com.platform.util.result.ResultCode.OPERATION_FAILURE;

@RestController
@RequestMapping("/user")
//说明接口文件
@Api(value = "用户接口", tags = "用户管理相关的接口", description = "用户测试接口")
public class UserController {
    @Autowired
    private UserService userService;

    //用户账号删除
    @PostMapping("/deleteUser")
    public RestResult deleteUser(@RequestParam String accountNumber){
        RestResult restResult = null;
        //删除图书
        boolean isDeleted = userService.deleteByNumber(accountNumber);
        if (isDeleted){
            //删除成功
            restResult =  RestResult.success();
        }else {
            //删除失败
            restResult = RestResult.failure(OPERATION_FAILURE);
        }
        return restResult;
    }

    //用户信息修改
    @PostMapping("/updateUser")
    public RestResult updateUser(@RequestBody User user){
        RestResult restResult = null;
        boolean isUpdate = userService.updateUser(user);
        if (isUpdate){
            //修改成功
            restResult =  RestResult.success();
        }else {
            //修改失败
            restResult = RestResult.failure(OPERATION_FAILURE);
        }
        return restResult;
    }

    //用户条件查找

}
