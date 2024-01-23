package com.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.pojo.Stock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.vo.StockVo;

/**
* @author 邓桂材
* @description 针对表【stock(库存表)】的数据库操作Service
* @createDate 2024-01-14 16:56:54
*/
public interface StockService extends IService<Stock> {

    Page<StockVo> selectStock(StockVo stockVo);

    boolean warehousing(Long id,Integer stockNum);

}
