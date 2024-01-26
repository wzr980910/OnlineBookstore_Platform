package com.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.Book;
import com.platform.pojo.vo.BookVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author wzr
* @description 针对表【book(图书表)】的数据库操作Mapper
* @createDate 2024-01-14 16:56:54
* @Entity com.bookStore.pojo.Book
*/
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    BookVo getBookByISBN(String isbn);

    void addBook(@Param("bookVo")BookVo bookVo);

    int listBooksById(List<Book> books);

    int removeBooksById(List<Book> books);

    int updateBook(@Param("bookVo")BookVo bookVo);

    IPage<BookVo> selectBookPage(IPage<?> page,BookVo bookVo);

    BookVo getBookDetailsById(Long id);

    Integer selectTotal(@Param("bookVo")BookVo bookVo);

}




