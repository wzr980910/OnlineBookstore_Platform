package com.platform.mapper;

import com.platform.pojo.BookType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.vo.BookVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 邓桂材
* @description 针对表【book_type】的数据库操作Mapper
* @createDate 2024-01-17 10:40:09
* @Entity com.bookStore.pojo.BookType
*/
@Mapper
public interface BookTypeMapper extends BaseMapper<BookType> {

    int addBookType(@Param("bookVo") BookVo bookVo);

    int updateBookType(@Param("bookVo")BookVo bookVo);
}




