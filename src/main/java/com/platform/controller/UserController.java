package com.platform.controller;


import com.platform.pojo.User;
import com.platform.pojo.vo.UserVo;
import com.platform.util.result.RestResult;
import com.platform.service.UserService;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

import static com.platform.util.result.ResultCode.OPERATION_FAILURE;

@RestController
@RequestMapping("/user")
//说明接口文件
@Api(value = "用户接口", tags = "用户管理相关的接口", description = "用户测试接口")
public class UserController {
    private UserService userService;
    @Autowired
    private void setUserController(UserService userService){this.userService = userService;}

    //用户账号注销(批量注销)
    @PostMapping("/removeUsers")
    public RestResult removeUsersById(@RequestBody List<User> users){
        //注销用户
        boolean isDeleted = userService.removeUsersById(users);
        if (isDeleted){
            //注销成功
            return RestResult.success();
        }else {
            //注销失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    //用户账号登记(批量登记)
    @PostMapping("/listUsers")
    public RestResult listUsersById(@RequestBody List<User> users){
        //注销用户
        boolean isDeleted = userService.listUsersById(users);
        if (isDeleted){
            //注销成功
            return RestResult.success();
        }else {
            //注销失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    //用户条件查找
    @PostMapping("/selectUser")
    public RestResult selectUser(@RequestBody UserVo userVo) {
        Map<String, Object> map = userService.selectUser(userVo);
        if (map != null) {
            //查询成功，包装数据返回
            return RestResult.success(map);
        } else {
            //查询失败
            return RestResult.failure("查询失败");
        }
    }
    //用户详情信息
    @PostMapping("/UserDetails")
    public RestResult getUserDetails(@RequestParam Long id){
        UserVo userVo = userService.getUserDetailsById(id);
        if (userVo != null) {
            //查询成功
            return RestResult.success(userVo);
        } else {
            //查询失败
            return RestResult.failure("获取详情信息失败");
        }
    }

}
