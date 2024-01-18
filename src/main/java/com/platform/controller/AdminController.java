package com.platform.controller;

import com.platform.pojo.Admin;
import com.platform.pojo.vo.AdminVo;
import com.platform.service.AdminService;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

import static com.platform.util.result.ResultCode.*;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/13/20:48
 * @Description:管理员操作类
 */
@Api(tags = "管理员操作")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //管理员账号添加

    //方法参数说明，name参数名；value参数说明，备注；dataType参数类型；required 是否必传；defaultValue 默认值
    @ApiImplicitParam(name = "admin", value = "新增管理员数据")
    //说明是什么方法(可以理解为方法注释)
    @ApiOperation(value = "添加管理员")
    @PostMapping("/addAdmin")
    public RestResult addadmin(@RequestBody Admin admin){
        RestResult restResult = null;
        //校验账号是否已存在
        Admin isAdmin = adminService.getByAdminName(admin.getAdminName());
        if(isAdmin != null) {
            restResult = RestResult.failure(USER_HAS_EXISTED);
        }else {
            //账号不存在,将该账号添加到系统
             boolean isAdd =  adminService.addAdmin(admin);
            if(isAdd){
                //添加成功,返回成功消息
                restResult = RestResult.success();
            }else{
                //添加失败
                restResult = RestResult.failure(USER_HAS_EXISTED);
            }
        }
        return restResult;
    }


    //管理员账号删除
    @ApiImplicitParam(name = "delete", value = "删除管理员数据")
    //说明是什么方法(可以理解为方法注释)
    @ApiOperation(value = "删除管理员")
    @PostMapping("/deleteAdmin")
    public RestResult deleteAdmin(@RequestParam String adminName){
        RestResult restResult = null;
        //删除账号
        boolean isDeleted = adminService.deleteAdmin(adminName);
        if (isDeleted){
            //删除成功
            restResult =  RestResult.success();
        }else {
            //删除失败
            restResult = RestResult.failure(OPERATION_FAILURE);
        }
        return restResult;
    }

    //管理员账号信息修改
    @PostMapping("/updateAdmin")
    public RestResult updateAdmin(@Validated@RequestBody Admin admin){
        RestResult restResult = null;
        boolean isUpdate = adminService.updateAdmin(admin);
        if (isUpdate){
            //修改成功
            restResult =  RestResult.success();
        }else {
            //修改失败
            restResult = RestResult.failure(OPERATION_FAILURE);
        }
        return restResult;
    }

    //管理员账号信息查询
    @PostMapping("/selectAdmin")
    public RestResult selectAdmin(AdminVo adminVo){
        RestResult restResult = null;
        Map<String,Object> map = adminService.selectAdmin(adminVo);
        //查询成功，包装数据返回
        restResult=new RestResult(ResultCode.SUCCESS,map);
        return restResult;
    }

}
