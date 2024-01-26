package com.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.Stock;
import com.platform.pojo.vo.BookVo;
import com.platform.pojo.vo.StockVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author wzr
* @description 针对表【stock(库存表)】的数据库操作Mapper
* @createDate 2024-01-14 16:56:54
* @Entity com.bookStore.pojo.Stock
*/
@Mapper
public interface StockMapper extends BaseMapper<Stock> {

    IPage<StockVo> selectStock(IPage<?> page, @Param("stockVo") StockVo stockVo);

    int warehousing(Long id,Integer stockNum);

    Integer selectTotal(@Param("stockVo") StockVo stockVo);

    int addStock(@Param("bookVo") BookVo bookVo);

}




