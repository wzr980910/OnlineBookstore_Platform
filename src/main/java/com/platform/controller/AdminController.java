package com.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.Admin;
import com.platform.pojo.vo.AdminVo;
import com.platform.pojo.vo.BookVo;
import com.platform.service.AdminService;
import com.platform.util.ThreadLocalUtil;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.HashMap;
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

@RestController
@RequestMapping("/admin")
@Api(value = "/admin",tags = "管理员操作")
public class AdminController {

    private AdminService adminService;
    @Autowired
    private AdminController(AdminService adminService){this.adminService = adminService;}

    //管理员账号添加
    //方法参数说明，name参数名；value参数说明，备注；dataType参数类型；required 是否必传；defaultValue 默认值
    //value 对操作的简单说明,notes 对操作的详细说明,httpMethod HTTP请求的动作名
    @ApiOperation(value = "添加",notes = "添加管理员",httpMethod = "POST")
    @PostMapping("/addAdmin")
    public RestResult addAdmin(@RequestBody Admin admin){
        //校验账号是否已存在
        Admin isAdmin = adminService.getByAdminName(admin.getAdminName());
        if(isAdmin != null) {
            return RestResult.failure(USER_HAS_EXISTED);
        }else {
            //账号不存在,将该账号添加到系统
             boolean isAdd =  adminService.addAdmin(admin);
            if(isAdd){
                //添加成功,返回成功消息
                return RestResult.success();
            }else{
                //添加失败
                return RestResult.failure(USER_HAS_EXISTED);
            }
        }
    }


    //管理员账号删除
    @ApiImplicitParam(name = "delete", value = "删除管理员数据")
    //说明是什么方法(可以理解为方法注释)
    @ApiOperation(value = "删除管理员")
    @PostMapping("/removeAdmin")
    public RestResult removeAdmin(@RequestParam Long id){
        //删除账号
        boolean isDeleted = adminService.removeAdminById(id);
        if (isDeleted){
            //删除成功
            return  RestResult.success();
        }else {
            //删除失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    //管理员账号信息修改
    @PostMapping("/updateAdmin")
    public RestResult updateAdmin(@Validated@RequestBody Admin admin){
        boolean isUpdate = adminService.updateAdmin(admin);
        if (isUpdate){
            //修改成功
            return  RestResult.success();
        }else {
            //修改失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    //管理员账号信息查询
    @PostMapping("/selectAdmin")
    public RestResult selectAdmin(@RequestBody AdminVo adminVo){
        IPage<AdminVo> page = adminService.selectAdmin(adminVo);
        if (page != null) {
            //查询成功，包装数据返回
            Map<String, Object> pageInfoMap = new HashMap<>();
            pageInfoMap.put("pageInfo", page);
            return RestResult.success(ResultCode.SUCCESS, "查询成功", pageInfoMap);
        } else {
            //查询失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    //批量注销
    @PostMapping("/removeAdminsById")
    public RestResult removeAdminsById(@RequestBody List<Admin> admins){
        boolean isListed = adminService.removeAdminsById(admins);
        if (isListed){
            return RestResult.success("注销成功");
        }else {
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    //批量登记
    @PostMapping("/listAdminsById")
    public RestResult listAdminsById(@RequestBody List<Admin> admins){
        boolean isListed = adminService.listAdminsById(admins);
        if (isListed){
            return RestResult.success("登记成功");
        }else {
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    //管理员添加/更新头像
    @PostMapping("/uploadAdminImg")
    public RestResult uploadAdminImg(@RequestParam(value = "file") MultipartFile file){
        Long adminId = ThreadLocalUtil.get();
        boolean isUpload = adminService.uploadAdminImg(adminId, file);
        if (isUpload){
            return RestResult.success(SUCCESS,"上传成功");
        }else {
            return RestResult.failure(OPERATION_FAILURE,"上传失败");
        }
    }


}
