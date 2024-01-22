package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.pojo.Book;
import com.platform.pojo.BookType;
import com.platform.pojo.PublishingHouse;
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
    public boolean addPublish(PublishingHouse publishingHouse) {
        //通过mapper层增添数据
        long id = publishingHouseMapper.addPublish(publishingHouse);
        //判断是否添加成功
        PublishingHouse isExisted = publishingHouseMapper.getPublishingHouseById(id);
        if (isExisted != null){
            //添加成功
            return true;
        }
        return false;
    }

    /*批量删除*/
    @Override
    public boolean removePublishsById(List<PublishingHouse> publishingHouses) {
        //设置删除状态
        for(PublishingHouse publishingHouse : publishingHouses){
            publishingHouse.setIsDeleted(IS_DELETE.getCode());
            publishingHouse.setUpdateTime(new Date());
        }
        //mapper层设置下架状态
        publishingHouseMapper.removePublishsById(publishingHouses);
        return true;
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
    public Map<String, Object> selectPublish(PublishingHouseVo publishingHouseVo) {
        //分页
        Page<Book> page = new Page<>(publishingHouseVo.getPageNum(), publishingHouseVo.getPageSize());
        //查询
        publishingHouseMapper.selectPublish(page, publishingHouseVo);
        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo", page);
        return pageInfoMap;
    }
}




