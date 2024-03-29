package com.platform.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.User;
import com.platform.pojo.vo.UserVo;
import com.platform.service.UserService;
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

import static com.platform.util.result.ResultCode.OPERATION_FAILURE;

@RestController
@RequestMapping("/user")
//说明接口文件
@Api(value = "用户接口", tags = "用户管理相关的接口", description = "用户测试接口")
public class UserController {
    private UserService userService;
    @Autowired
    private void setUserController(UserService userService){this.userService = userService;}

    @ApiOperation(value = "用户账号注销(批量注销)", notes = "传参类型为List<User>")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/removeUsers")
    public RestResult removeUsersById(@RequestBody List<User> users){
        //注销用户
        int row = userService.removeUsersById(users);
        if (row > 0){
            //注销成功
            return RestResult.success();
        }else {
            //注销失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    //
    @ApiOperation(value = "用户账号登记(批量登记)", notes = "传参类型为List<User>")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/listUsers")
    public RestResult listUsersById(@RequestBody List<User> users){
        //登记用户
        int row = userService.listUsersById(users);
        if (row > 0){
            //登记成功
            return RestResult.success();
        }else {
            //登记失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    @ApiOperation(value = "用户条件查找", notes = "传参类型为UserVo")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/selectUser")
    public RestResult selectUser(@RequestBody UserVo userVo) {
        IPage<UserVo> page = userService.selectUser(userVo);
        Integer userTotal = userService.selectTotal(userVo);
        if (page != null) {
            //查询成功，包装数据返回
            Map<String, Object> pageInfoMap = new HashMap<>();
            pageInfoMap.put("pageInfo", page);
            pageInfoMap.put("total", userTotal);
            return RestResult.success(ResultCode.SUCCESS, "查询成功", pageInfoMap);
        } else {
            //查询失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }
    @ApiOperation(value = "用户详情信息", notes = "传参类型为id")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @GetMapping("/UserDetails")
    public RestResult getUserDetails(@RequestParam(value = "id") Long id){
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
