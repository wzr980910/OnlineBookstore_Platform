package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.mapper.StockMapper;
import com.platform.pojo.Stock;
import com.platform.pojo.vo.StockVo;
import com.platform.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author wzr
* @description 针对表【stock(库存表)】的数据库操作Service实现
* @createDate 2024-01-14 16:56:54
*/
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock>
    implements StockService{

    private StockMapper stockMapper;
    @Autowired
    private void setStockServiceImpl(StockMapper stockMapper){this.stockMapper = stockMapper;}

    /**
     * 查询库存
     */
    @Override
    public Page<StockVo> selectStock(StockVo stockVo) {
        //分页
        Page<StockVo> page = new Page<>(stockVo.getCurrent(), stockVo.getSize());
        //查询
        stockMapper.selectStock(page, stockVo);
        return page;
    }

    /**
     * 入库
     */
    @Override
    public int warehousing(Long id, Integer stockNum) {
        //通过mapper层入库
        return stockMapper.warehousing(id,stockNum);
    }

    /**
     * 查询库存总数
     */
    @Override
    public Integer selectTotal(StockVo stockVo) {
        return stockMapper.selectTotal(stockVo);
    }
}




