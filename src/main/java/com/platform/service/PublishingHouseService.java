package com.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.PublishingHouse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.vo.PublishingHouseVo;

import java.util.List;
import java.util.Map;

/**
* @author wzr
* @description 针对表【publishing_house(出版社信息)】的数据库操作Service
* @createDate 2024-01-14 16:56:54
*/
public interface PublishingHouseService extends IService<PublishingHouse> {

    //添加出版社
    boolean addPublish(PublishingHouse publishingHouse);

    //(批量)删除
    boolean removePublishsById(List<PublishingHouse> publishingHouses);

    //(批量)登记出版社
    boolean listPublishsById(List<PublishingHouse> publishingHouses);

    boolean updatePublish(PublishingHouse publishingHouse);

    IPage<PublishingHouseVo> selectPublish(PublishingHouseVo publishingHouseVo);
}
