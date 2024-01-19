package com.platform.service;

import com.platform.pojo.PublishingHouse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.vo.PublishingHouseVo;

import java.util.Map;

/**
* @author 邓桂材
* @description 针对表【publishing_house(出版社信息)】的数据库操作Service
* @createDate 2024-01-14 16:56:54
*/
public interface PublishingHouseService extends IService<PublishingHouse> {

    //添加图书
    boolean addPublish(PublishingHouse publishingHouse);

    //删除图书
    boolean deleteById(long id);

    boolean updatePublish(PublishingHouse publishingHouse);

    Map<String, Object> selectPublish(PublishingHouseVo publishingHouseVo);
}
