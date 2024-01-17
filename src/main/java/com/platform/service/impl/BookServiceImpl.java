package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.mapper.BookTypeMapper;
import com.platform.pojo.Admin;
import com.platform.pojo.Book;
import com.platform.pojo.BookType;
import com.platform.pojo.vo.BookVo;
import com.platform.service.BookService;
import com.platform.mapper.BookMapper;
import com.platform.util.CreateISBNUtil;
import com.platform.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.platform.common.DeleteState.IS_DELETE;
import static com.platform.common.DeleteState.NO_DELETE;

/**
* @author wzr
* @description 针对表【book(图书表)】的数据库操作Service实现
* @createDate 2024-01-14 16:56:54
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookTypeMapper bookTypeMapper;

    @Override
    public Book getBookByISBN(String ISBN) {
        //通过mapper层进行查询
        return bookMapper.getBookByISBN(ISBN);
    }

    @Override
    public boolean addBook(BookVo bookVo) {
        Book book = null;
        BookType bookType = null;
        //首先存入图书,如果成功则将图书与对应的类型关系存入book_type表中
        //创建ISBN唯一标识码并判断是否唯一
        String bookISBN = CreateISBNUtil.createISBN();
        Book isOnly = getBookByISBN(bookISBN);
        while (isOnly != null){
            //编码不唯一 继续生成
            bookISBN = CreateISBNUtil.createISBN();
            isOnly = getBookByISBN(bookISBN);
        }
        //添加图书信息
        /*book.setISBN(bookISBN);
        book.setBookName(bookVo.getBookName());
        book.setAuthor(bookVo.getAuthor());
        book.setPublishId(bookVo.getPublishId());
        book.setPublishDate(bookVo.getPublishDate());
        book.setPrice(bookVo.getPrice());
        book.setPicture(bookVo.getPicture());
        book.setContent(bookVo.getContent());*/
        bookVo.setIsDeleted(NO_DELETE.getCode());
        //通过mapper层增添数据
        bookMapper.addBook(bookVo);
        //判断是否添加成功
        Book isExisted = bookMapper.getBookByISBN(bookVo.getISBN());
        if (isExisted != null){
            //添加成功,将图书与类型信息添加到book_type表中
            /*bookType.setBookId(bookMapper.getBookByISBN(bookISBN).getId());
            bookType.setTypeId(bookVo.getTypeId());*/
            bookTypeMapper.addBookType(bookVo);
            return true;
        }
        return false;
    }

    //删除图书
    @Override
    public boolean deleteByISBN(String ISBN) {
        //设置删除状态
        int isDeleted = NO_DELETE.getCode();
        //通过mapper层进行删除
        bookMapper.deleteByISBN(ISBN,new Date());
        //判断是否删除
        Book isDelete = bookMapper.getBookByISBN(ISBN);
        if (isDelete.getIsDeleted().equals(IS_DELETE.getCode())){
            //删除成功
            return true;
        }
        return false;
    }

    //修改图书
    @Override
    public boolean updateBook(BookVo bookVo) {
        /*Book book = null;
        BookType bookType = null;
        //获取Book类数据
        book.setISBN(book.getISBN());
        book.setBookName(bookVo.getBookName());
        book.setAuthor(bookVo.getAuthor());
        book.setPublishId(bookVo.getPublishId());
        book.setPublishDate(bookVo.getPublishDate());
        book.setPrice(bookVo.getPrice());
        book.setPicture(bookVo.getPicture());
        book.setContent(bookVo.getContent());*/
        bookVo.setIsDeleted(NO_DELETE.getCode());
        bookMapper.updateBook(bookVo);
        //获取bookType数据,修改图书类型
        /*bookType.setBookId(bookMapper.getBookByISBN(book.getISBN()).getId());
        bookType.setTypeId(bookVo.getTypeId());*/
        bookTypeMapper.updateBookType(bookVo);
        //修改成功
        return true;
    }



}




