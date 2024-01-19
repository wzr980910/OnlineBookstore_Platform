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
import java.util.Map;

import static com.platform.common.DeleteState.IS_DELETE;
import static com.platform.common.DeleteState.NO_DELETE;

/**
* @author 邓桂材
* @description 针对表【publishing_house(出版社信息)】的数据库操作Service实现
* @createDate 2024-01-14 16:56:54
*/
@Service
public class PublishingHouseServiceImpl extends ServiceImpl<PublishingHouseMapper, PublishingHouse>
    implements PublishingHouseService{

    @Autowired
    private PublishingHouseMapper publishingHouseMapper;

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

    @Override
    public boolean deleteById(long id) {
        //设置删除状态
        int isDeleted = NO_DELETE.getCode();
        //通过mapper层进行删除
        publishingHouseMapper.deleteById(id,isDeleted,new Date());
        //判断是否删除
        PublishingHouse isDelete = publishingHouseMapper.getPublishingHouseById(id);
        if (isDelete.getIsDeleted().equals(IS_DELETE.getCode())){
            //删除成功
            return true;
        }
        return false;
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
        //封装查询到的内容
        Map<String, Object> pageInfo = new HashMap<>();
        //从page中获得返回的数据，作为value放入map中，对应k值为pageData
        pageInfo.put("pageData", page.getRecords());
        //从page中返回当前是第几页
        pageInfo.put("pageNum", page.getCurrent());
        //从page中返回当前页容量
        pageInfo.put("pageSize", page.getSize());
        //返回总页数
        pageInfo.put("totalPage", page.getPages());
        //返回结果总条数
        pageInfo.put("totalSize", page.getTotal());
        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo", pageInfo);
        return pageInfoMap;
    }
}




