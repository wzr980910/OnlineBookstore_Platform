package com.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.Slide;

import java.util.ArrayList;
import java.util.Map;

/**
* @author wzr
* @description 针对表【slide】的数据库操作Service
* @createDate 2024-01-22 11:08:14
*/
public interface SlideService extends IService<Slide> {
    Integer addSlide(Slide slide);

    Integer updateSlide(Slide slide);

    Map<String,Object> querySlidePage(Integer current, Integer size);

    Integer deleteSlide(ArrayList<Long> ids);

    Integer listSlide(ArrayList<Long> ids);

    Integer selectTotal();

}
