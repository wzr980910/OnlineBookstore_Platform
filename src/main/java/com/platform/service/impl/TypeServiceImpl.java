package com.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.pojo.Type;
import com.platform.pojo.respojo.TypeGrade;
import com.platform.service.TypeService;
import com.platform.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 邓桂材
* @description 针对表【type(图书类型表)】的数据库操作Service实现
* @createDate 2024-01-14 16:56:54
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Map<String, Object> queryAllType(Integer currentPage, Integer size) {
        Page<TypeGrade> page = new Page<>(currentPage, size);
        typeMapper.selectPageType(page);
        //封装查询到的内容
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageData", page.getRecords());
        pageInfo.put("pageNum", page.getCurrent());
        pageInfo.put("pageSize", page.getSize());
        pageInfo.put("totalPage", page.getPages());
        pageInfo.put("totalSize", page.getTotal());
        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo", pageInfo);
        return pageInfoMap;
    }

    @Override
    public List<Type> queryByParentId(Long parentId) {
        QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId", parentId);
        return typeMapper.selectList(queryWrapper);
    }

    @Override
    public Integer insertParentType(String parentType) {
        Type type = new Type();
        type.setType(parentType);
        //父类的parentId为 0;
        type.setParentId(Long.valueOf("0"));
        return typeMapper.insert(type);
    }

    @Override
    public Integer insertType(Type type) {
        return typeMapper.insert(type);
    }

    @Override
    public Integer deleteType(Long typeId) {
        QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId", typeId);
        Long count = typeMapper.selectCount(queryWrapper);
        if (count == 0){
            return typeMapper.deleteById(typeId);
        }
        //删除所有子类级别
        typeMapper.delete(queryWrapper);
        //删除父类级别
        return typeMapper.deleteById(typeId);
    }

    @Override
    public Integer updateType(Type type) {
        return typeMapper.updateById(type);
    }
}




