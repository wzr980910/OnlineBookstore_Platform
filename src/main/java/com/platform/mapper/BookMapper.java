package com.platform.mapper;

import com.platform.pojo.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
* @author 邓桂材
* @description 针对表【book(图书表)】的数据库操作Mapper
* @createDate 2024-01-14 16:56:54
* @Entity com.bookStore.pojo.Book
*/
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    Book getBookByISBN(String ISBN);

    void addBook(Book book);


    void deleteByISBN(String ISBN, Date updateTime);
}




