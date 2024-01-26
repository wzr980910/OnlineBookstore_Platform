package com.platform.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.pojo.Book;
import com.platform.pojo.PublishingHouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.vo.PublishingHouseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    int addPublish(PublishingHouse publishingHouse);

    /*(批量)删除*/
    int removePublishsById(List<PublishingHouse> publishingHouses);


    /*(批量)登记*/
    void listPublishsById(List<PublishingHouse> publishingHouses);

    /**
     * 更新
     */
    void updatePublish(PublishingHouse publishingHouse);

    //查询出版社
    IPage<PublishingHouse> selectPublish(Page<?> page, PublishingHouseVo publishingHouseVo);

    //查询出版社数量
    PublishingHouseVo selectNumber(@Param("publishingHouseVo") PublishingHouseVo publishingHouseVo);

    //查询出版社名称
    PublishingHouseVo selectPublishName(@Param("id") Long id);
}




