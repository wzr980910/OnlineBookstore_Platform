package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.pojo.Book;
import com.platform.pojo.BookType;
import com.platform.pojo.PublishingHouse;
import com.platform.pojo.vo.AdminVo;
import com.platform.pojo.vo.PublishingHouseVo;
import com.platform.service.PublishingHouseService;
import com.platform.mapper.PublishingHouseMapper;
import com.platform.util.CreateISBNUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.platform.common.DeleteState.IS_DELETE;
import static com.platform.common.DeleteState.NO_DELETE;

/**
* @author wzr
* @description 针对表【publishing_house(出版社信息)】的数据库操作Service实现
* @createDate 2024-01-14 16:56:54
*/
@Service
public class PublishingHouseServiceImpl extends ServiceImpl<PublishingHouseMapper, PublishingHouse>
    implements PublishingHouseService{
    private PublishingHouseMapper publishingHouseMapper;
    @Autowired
    private void setPublishingHouseServiceImpl(PublishingHouseMapper publishingHouseMapper){this.publishingHouseMapper = publishingHouseMapper;}

    @Override
    public int addPublish(PublishingHouse publishingHouse) {
        //通过mapper层增添数据
        return publishingHouseMapper.addPublish(publishingHouse);
    }

    /*批量删除*/
    @Override
    public int removePublishsById(List<PublishingHouse> publishingHouses) {
        //设置删除状态
        for(PublishingHouse publishingHouse : publishingHouses){
            publishingHouse.setIsDeleted(IS_DELETE.getCode());
            publishingHouse.setUpdateTime(new Date());
        }
        //mapper层设置下架状态
        return publishingHouseMapper.removePublishsById(publishingHouses);
    }

    /*批量登记*/
    @Override
    public boolean listPublishsById(List<PublishingHouse> publishingHouses) {
        //设置登记状态
        for(PublishingHouse publishingHouse : publishingHouses){
            publishingHouse.setIsDeleted(NO_DELETE.getCode());
            publishingHouse.setUpdateTime(new Date());
        }
        //mapper层设置下架状态
        publishingHouseMapper.listPublishsById(publishingHouses);
        return true;
    }

    @Override
    public boolean updatePublish(PublishingHouse publishingHouse) {
        //修改出版社
        publishingHouseMapper.updatePublish(publishingHouse);
        //修改成功
        return true;
    }

    @Override
    public Page<PublishingHouseVo> selectPublish(PublishingHouseVo publishingHouseVo) {
        //分页
        Page<PublishingHouseVo> page = new Page<>(publishingHouseVo.getCurrent(), publishingHouseVo.getSize());
        //查询
        publishingHouseMapper.selectPublish(page, publishingHouseVo);
        return page;
    }

    @Override
    public PublishingHouseVo selectNumber(PublishingHouseVo publishingHouseVo) {
        return publishingHouseMapper.selectNumber(publishingHouseVo);
    }
}




