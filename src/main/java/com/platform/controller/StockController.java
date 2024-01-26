package com.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.vo.StockVo;
import com.platform.service.StockService;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/22/20:36
 * @Description:
 */
@RestController
@RequestMapping("/stock")
@Api(value = "库存接口", tags = "库存相关的接口", description = "库存测试接口")
public class StockController {
    private StockService stockService;

    @Autowired
    private void setStockService(StockService stockService){this.stockService = stockService;}

    @ApiOperation(value = "查询库存", notes = "传参类型为StockVo实体类")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/selectStock")
    public RestResult selectStock(@RequestBody StockVo stockVo){
        IPage<StockVo> page = stockService.selectStock(stockVo);
        Integer stockTotal = stockService.selectTotal(stockVo);
        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo", page);
        pageInfoMap.put("total", stockTotal);
        //查询成功，包装数据返回
        return new RestResult(ResultCode.SUCCESS,pageInfoMap);
    }


    @ApiOperation(value = "入库", notes = "传参类型为库存id和入库数量stockNum")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @GetMapping("/warehousing")
    public RestResult warehousing(@RequestParam(value = "id") Long id,@RequestParam(value = "stockNum") Integer stockNum){
        int row = stockService.warehousing(id,stockNum);
        if (row > 0) {
            //入库成功
            return RestResult.success();
        } else {
            //入库失败
            return RestResult.failure(ResultCode.OPERATION_FAILURE,"入库失败");
        }
    }

}
