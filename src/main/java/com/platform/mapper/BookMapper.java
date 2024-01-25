package com.platform.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.pojo.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.vo.BookVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author wzr
* @description 针对表【book(图书表)】的数据库操作Mapper
* @createDate 2024-01-14 16:56:54
* @Entity com.bookStore.pojo.Book
*/
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    Book getBookByISBN(String ISBN);


    void addBook(BookVo bookVo);

    int listBooksById(List<Book> books);

    int removeBooksById(List<Book> books);

    int updateBook(BookVo bookVo);

    IPage<BookVo> selectBookPage(IPage<?> page,BookVo bookVo);

    BookVo getBookDetailsById(Long id);

    BookVo selectNumber(@Param("bookVo")BookVo bookVo);
}




