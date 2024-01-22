package com.platform.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.pojo.Book;
import com.platform.pojo.PublishingHouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.vo.PublishingHouseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
* @author 邓桂材
* @description 针对表【publishing_house(出版社信息)】的数据库操作Mapper
* @createDate 2024-01-14 16:56:54
* @Entity com.bookStore.pojo.PublishingHouse
*/
@Mapper
public interface PublishingHouseMapper extends BaseMapper<PublishingHouse> {

    long addPublish(PublishingHouse publishingHouse);

    PublishingHouse getPublishingHouseById(Long id);

    /*(批量)删除*/
    void removePublishsById(List<PublishingHouse> publishingHouses);


    /*(批量)登记*/
    void listPublishsById(List<PublishingHouse> publishingHouses);
    void updatePublish(PublishingHouse publishingHouse);

    IPage<PublishingHouse> selectPublish(Page<?> page, PublishingHouseVo publishingHouseVo);
}




