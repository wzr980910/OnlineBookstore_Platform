package com.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.vo.BookVo;
import com.platform.pojo.vo.StockVo;
import com.platform.service.StockService;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.Api;
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
@Api(value = "/stock",tags = "库存管理")
public class StockController {
    private StockService stockService;

    private StockController(StockService stockService){this.stockService = stockService;}

    /*查询库存*/
    @PostMapping("/selectStock")
    public RestResult selectStock(@RequestBody StockVo stockVo){
        IPage<StockVo> page = stockService.selectStock(stockVo);
        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo", page);
        //查询成功，包装数据返回
        return new RestResult(ResultCode.SUCCESS,pageInfoMap);
    }


    /*入库*/
    @PostMapping("/warehousing")
    public RestResult warehousing(@RequestParam Long id,@RequestParam Integer stockNum){
        boolean isWarehousing = stockService.warehousing(id,stockNum);
        //入库成功
        return RestResult.success();
    }

}
