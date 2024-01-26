package com.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.Slide;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
* @author 邓桂材
* @description 针对表【slide】的数据库操作Mapper
* @createDate 2024-01-22 11:08:14
* @Entity com.bookStore.pojo.Slide
*/
@Mapper
public interface SlideMapper extends BaseMapper<Slide> {
    IPage<Slide> queryAllPage(IPage<?> page);

    Integer deleteBatchSlideIds(ArrayList<Long> ids);

    Integer listBatchSlideIds(ArrayList<Long> ids);

    Integer selectTotal();

}




