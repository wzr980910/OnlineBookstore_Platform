package com.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.PublishingHouse;
import com.platform.pojo.vo.PublishingHouseVo;

import java.util.List;

/**
* @author wzr
* @description 针对表【publishing_house(出版社信息)】的数据库操作Service
* @createDate 2024-01-14 16:56:54
*/
public interface PublishingHouseService extends IService<PublishingHouse> {

    //添加出版社
    int addPublish(PublishingHouse publishingHouse);

    //(批量)删除
    int removePublishsById(List<PublishingHouse> publishingHouses);

    //(批量)登记出版社
    boolean listPublishsById(List<PublishingHouse> publishingHouses);

    boolean updatePublish(PublishingHouse publishingHouse);

    //查询出版社
    IPage<PublishingHouseVo> selectPublish(PublishingHouseVo publishingHouseVo);

    //查询出版社总数
    Integer selectNumber(PublishingHouseVo publishingHouseVo);
}
