package com.platform.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.Slide;

import java.util.List;
import java.util.Map;

/**
* @author 邓桂材
* @description 针对表【slide】的数据库操作Service
* @createDate 2024-01-22 11:08:14
*/
public interface SlideService extends IService<Slide> {
    Integer addSlide(Slide slide);

    Integer updateSlide(Slide slide);

    Map<String,Object> querySlidePage(Integer currentPage, Integer size);

    Integer deleteSlide(List<String> ids);

}
