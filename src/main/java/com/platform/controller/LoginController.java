package com.platform.controller;

import com.platform.pojo.Admin;
import com.platform.service.AdminService;
import com.platform.util.JwtHelper;
import com.platform.util.result.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.platform.util.result.ResultCode.USER_LOGIN_ERROR;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/15/16:04
 * @Description:登录操作类
 */
@Api(value = "接口", tags = "管理员登录")
@RestController
@RequestMapping("/adminLogin")
public class LoginController {
    @Autowired
    private AdminService adminService;

    //管理员登录操作
    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public RestResult login(@RequestBody Admin admin) {
        RestResult restResult = null;
        //获取账号密码
        String adminName = admin.getAdminName();
        String password = admin.getPassword();
        if(!adminName.trim().equals("") && !password.trim().equals("")) {
            restResult = adminService.login(adminName, password);
        } else {
            restResult = RestResult.failure(USER_LOGIN_ERROR);
        }
        return restResult;
    }
}
