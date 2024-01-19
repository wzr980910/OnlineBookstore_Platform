package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import java.util.HashMap;
import java.util.Map;

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
        bookVo.setIsDeleted(NO_DELETE.getCode());
        //通过mapper层增添数据
        bookMapper.addBook(bookVo);
        //判断是否添加成功
        Book isExisted = bookMapper.getBookByISBN(bookVo.getISBN());
        if (isExisted != null){
            //添加成功,将图书与类型信息添加到book_type表中
            bookTypeMapper.addBookType(bookVo);
            return true;
        }
        return false;
    }

    //删除图书
    @Override
    public boolean deleteByISBN(String ISBN) {
        //设置删除状态
        Integer isDeleted = NO_DELETE.getCode();
        //通过mapper层进行删除
        bookMapper.deleteByISBN(ISBN,isDeleted,new Date());
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
        bookMapper.updateBook(bookVo);
        //获取bookType数据,修改图书类型
        bookTypeMapper.updateBookType(bookVo);
        //修改成功
        return true;
    }

    @Override
    public Map<String, Object> selectBookPage(BookVo bookVo) {
        //分页
        Page<Book> page = new Page<>(bookVo.getPageNum(), bookVo.getPageSize());
        //查询
        bookMapper.selectBookPage(page, bookVo);
        //封装查询到的内容
        Map<String, Object> pageInfo = new HashMap<>();
        //从page中获得返回的数据，作为value放入map中，对应k值为pageData
        pageInfo.put("pageData", page.getRecords());
        //从page中返回当前是第几页
        pageInfo.put("pageNum", page.getCurrent());
        //从page中返回当前页容量
        pageInfo.put("pageSize", page.getSize());
        //返回总页数
        pageInfo.put("totalPage", page.getPages());
        //返回结果总条数
        pageInfo.put("totalSize", page.getTotal());
        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo", pageInfo);
        return pageInfoMap;
    }


}




