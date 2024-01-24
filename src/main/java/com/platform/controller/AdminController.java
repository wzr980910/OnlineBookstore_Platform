package com.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.Admin;
import com.platform.pojo.vo.AdminVo;
import com.platform.pojo.vo.BookVo;
import com.platform.service.AdminService;
import com.platform.util.ThreadLocalUtil;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.*;
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
@Api(value = "管理员接口", tags = "地址相关的接口", description = "地址测试接口")
public class AdminController {

    private AdminService adminService;
    @Autowired
    private void setAdminService(AdminService adminService){this.adminService = adminService;}

    @ApiOperation(value = "添加管理员", notes = "添加管理员,此时管理员Id不需要")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
            @ApiResponse(code = 2005,message = "管理员已存在")
    })
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
                return RestResult.failure(OPERATION_FAILURE);
            }
        }
    }

    @ApiImplicitParam(name="removeAdmin",value="需要删除管理员的Id",required = true,paramType = "form")
    @ApiOperation(value = "删除管理员", notes = "删除管理员删除管理员")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
                @ApiResponse(code = 1004,message = "参数缺失")
    })
    @PostMapping("/removeAdmin")
    public RestResult removeAdmin(@RequestParam Long id){
        //没有传入id
        if(id == null){
            return RestResult.failure(PARAM_NOT_COMPLETE);
        }
        //删除账号
        int row = adminService.removeAdminById(id);
        if (row > 0){
            //删除成功
            return  RestResult.success();
        }else {
            //删除失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    @ApiOperation(value = "修改管理员信息", notes = "修改管理员信息")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
            @ApiResponse(code = 1004,message = "参数缺失")
    })
    @PostMapping("/updateAdmin")
    public RestResult updateAdmin(@Validated@RequestBody Admin admin){
        //没有传入id
        if(admin.getId() == null){
            return RestResult.failure(PARAM_NOT_COMPLETE);
        }
        //修改管理员
        int row = adminService.updateAdmin(admin);
        if (row > 0){
            //修改成功
            return  RestResult.success();
        }else {
            //修改失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    @ApiOperation(value = "查询单个管理员/全部管理员", notes = "传入参数为空查询全部,或通过参数查询特定管理员")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
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

    @ApiOperation(value = "注销单个管理员/全部管理员", notes = "传入格式为List<Admin>")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/removeAdminsById")
    public RestResult removeAdminsById(@RequestBody List<Admin> admins){
        int row = adminService.removeAdminsById(admins);
        if (row > 0){
            return RestResult.success("注销成功");
        }else {
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    @ApiOperation(value = "登记单个管理员/全部管理员", notes = "传入格式为List<Admin>")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/listAdminsById")
    public RestResult listAdminsById(@RequestBody List<Admin> admins){
        int row = adminService.listAdminsById(admins);
        if (row > 0){
            return RestResult.success("登记成功");
        }else {
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    @ApiOperation(value = "更新头像", notes = "传入格式为文件file")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/uploadAdminImg")
    public RestResult uploadAdminImg(@RequestParam(value = "file") MultipartFile file){
        Long adminId = ThreadLocalUtil.get();
        int row = adminService.uploadAdminImg(adminId, file);
        if (row > 0){
            return RestResult.success(SUCCESS,"上传成功");
        }else {
            return RestResult.failure(OPERATION_FAILURE,"上传失败");
        }
    }
}
