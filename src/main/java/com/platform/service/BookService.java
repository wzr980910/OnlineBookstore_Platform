package com.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.pojo.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.vo.BookVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
* @author wzr
* @description 针对表【book(图书表)】的数据库操作Service
* @createDate 2024-01-14 16:56:54
*/
@Service
public interface BookService extends IService<Book> {

    /**
     * 通过ISBN查询图书
     */
    Book getBookByISBN(String isbn);

    /**
     * 添加图书
     */
    int addBook(BookVo bookVo);

    /**
     * 查询图书数量
     */
    BookVo selectTotal(BookVo bookVo);

    /**
     * 批量上架
     */
    int listBooksById(List<Book> books);

    /**
     * 批量下架
     */
    int removeBooksById(List<Book> books);

    /**
     * 修改图书
     */
    int updateBook(BookVo bookVo);

    /**
     * 根据条件查询图书
     */
    Page<BookVo> selectBookPage(BookVo bookVo);


    /**
     * 查询图书详情
     */
    BookVo getBookDetailsById(Long id);

    /**
     * 上传图书封面
     */
    String uploadBookImg(MultipartFile file);
}
