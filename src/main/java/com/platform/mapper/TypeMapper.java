package com.platform.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.respojo.TypeGrade;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wzr
* @description 针对表【type(图书类型表)】的数据库操作Mapper
* @createDate 2024-01-14 16:56:54
* @Entity com.bookStore.pojo.Type
*/
@Mapper
public interface TypeMapper extends BaseMapper<Type> {
    IPage<TypeGrade> selectPageType(IPage<?> page);
}




