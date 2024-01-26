package com.platform.controller;

import com.platform.pojo.Type;
import com.platform.service.TypeService;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/18/11:05
 * @Description:类型表操作类
 */
@RestController
@RequestMapping("/type")
@Api(value = "类别接口", tags = "类别相关的接口", description = "类别测试接口")
public class TypeController {
    private TypeService typeService;
    @Autowired
    private void setTypeService(TypeService typeService){this.typeService = typeService;}

    @GetMapping(value = "/queryAllType")
    @ApiOperation(value = "查询所有类别信息", notes = "不需要传参数")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    public RestResult queryAllType(@RequestParam(defaultValue = "1") Integer currentPage,
                                   @RequestParam(defaultValue = "10") Integer size) {
        Map<String, Object> map = typeService.queryAllType(currentPage, size);
        if (map != null) {
            return RestResult.success(ResultCode.SUCCESS, "查询成功", map);
        }
        return RestResult.failure("查询失败");
    }


    @GetMapping(value = "/queryByParentId")
    @ApiOperation(value = "根据父类查询所有子类信息", notes = "parentId 必填")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    public RestResult queryByParentId(@RequestParam Long parentId) {
        List<Type> types = typeService.queryByParentId(parentId);
        if (types == null) {
            return RestResult.failure(ResultCode.OPERATION_FAILURE, "查询失败");
        } else {
            return RestResult.success(ResultCode.SUCCESS, "查询成功", types);
        }
    }


    @PostMapping(value = "/addParentType")
    @ApiOperation(value = "添加父类", notes = "parentType 必填")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    public RestResult addParentType(@RequestParam String parentType) {
        Integer rows = typeService.insertParentType(parentType);
        if (rows > 0) {
            return RestResult.success(ResultCode.SUCCESS, "添加成功",rows);
        } else {
            return RestResult.failure(ResultCode.OPERATION_FAILURE, "添加失败");
        }
    }


    @PostMapping(value = "/addType")
    @ApiOperation(value = "添加子类类别", notes = "type parentId 必填")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    public RestResult addType(@RequestBody Type type) {
        Integer rows = typeService.insertType(type);
        if (rows > 0) {
            return RestResult.success(ResultCode.SUCCESS, "添加成功",rows);
        } else {
            return RestResult.failure(ResultCode.OPERATION_FAILURE, "添加失败");
        }
    }

    @PostMapping(value = "/deleteType")
    @ApiOperation(value = "删除图书类别", notes = "typeId 必填 不管是删除父类别，还是删除子类别都用该接口")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    public RestResult deleteType(@RequestParam Long typeId) {
        Integer rows = typeService.deleteType(typeId);
        if (rows > 0) {
            return RestResult.success(ResultCode.SUCCESS, "删除成功",rows);
        } else {
            return RestResult.failure(ResultCode.OPERATION_FAILURE, "删除失败");
        }
    }

    @PostMapping(value = "/updateType")
    @ApiOperation(value = "更新图书类别", notes = "typeId 必填")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    public RestResult updateType(@RequestBody Type type) {
        Integer rows = typeService.updateType(type);
        if (rows > 0) {
            return RestResult.success(ResultCode.SUCCESS, "更新成功",rows);
        } else {
            return RestResult.failure(ResultCode.OPERATION_FAILURE, "更新失败");
        }
    }
}
