package com.platform.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.mapper.SlideMapper;
import com.platform.pojo.Slide;
import com.platform.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wzr
 * @description 针对表【slide】的数据库操作Service实现
 * @createDate 2024-01-22 11:08:14
 */
@Service
public class SlideServiceImpl extends ServiceImpl<SlideMapper, Slide>
        implements SlideService {

    private SlideMapper slideMapper;
    @Autowired
    public void setSlideMapper(SlideMapper slideMapper){this.slideMapper = slideMapper;}

    @Override
    public Integer addSlide(Slide slide) {
        return slideMapper.insert(slide);
    }

    @Override
    public Integer updateSlide(Slide slide) {
        return slideMapper.updateById(slide);
    }

    @Override
    public Map<String,Object> querySlidePage(Integer current, Integer size) {
        Page<Slide> page = new Page<>(current,size);
        IPage<Slide> slideIPage = slideMapper.queryAllPage(page);
        Map<String,Object> pageInfo = new HashMap<>();
        pageInfo.put("pageInfo",slideIPage);
        return pageInfo;
    }

    @Override
    public Integer deleteSlide(ArrayList<Long> ids) {
        return slideMapper.deleteBatchSlideIds(ids);
    }

    @Override
    public Integer listSlide(ArrayList<Long> ids) {
        return slideMapper.listBatchSlideIds(ids);
    }

    @Override
    public Integer selectTotal() {
        return slideMapper.selectTotal();
    }
}




