package com.platform.service;

import com.platform.pojo.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.vo.BookVo;
import org.springframework.stereotype.Service;

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


    //删除图书
    boolean deleteByISBN(String ISBN);

    //修改图书
    boolean updateBook(BookVo bookVo);
}
