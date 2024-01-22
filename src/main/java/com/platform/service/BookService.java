package com.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.pojo.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.vo.BookVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author 邓桂材
* @description 针对表【book(图书表)】的数据库操作Service
* @createDate 2024-01-14 16:56:54
*/
@Service
public interface BookService extends IService<Book> {

    //通过ISBN查询图书
    Book getBookByISBN(String ISBN);

    //添加图书
    boolean addBook(BookVo bookVo);

    //下架图书
    boolean removeById(long id);

    //上架图书
    boolean listById(long id);

    //批量上架
    boolean listBooksById(List<Book> books);

    //批量下架
    boolean removeBooksById(List<Book> books);

    //修改图书
    boolean updateBook(BookVo bookVo);

    //根据条件查询图书
    Page<BookVo> selectBookPage(BookVo bookVo);

    //查询图书详情
    BookVo getBookDetailsById(long id);
}
