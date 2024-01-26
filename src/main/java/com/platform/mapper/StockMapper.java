package com.platform.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.Admin;
import com.platform.pojo.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.vo.AdminVo;
import com.platform.pojo.vo.StockVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

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

    StockVo selectTotal(@Param("stockVo") StockVo stockVo);

}




