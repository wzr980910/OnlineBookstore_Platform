package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.mapper.BookMapper;
import com.platform.mapper.BookTypeMapper;
import com.platform.mapper.StockMapper;
import com.platform.mapper.TypeMapper;
import com.platform.pojo.Book;
import com.platform.pojo.Type;
import com.platform.pojo.vo.BookVo;
import com.platform.service.BookService;
import com.platform.util.CreateISBNUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    private BookMapper bookMapper;
    private BookTypeMapper bookTypeMapper;
    private TypeMapper typeMapper;
    private StockMapper stockMapper;

    @Autowired
    private void setBookMapper(BookMapper bookMapper){this.bookMapper = bookMapper;}
    @Autowired
    private void setBookTypeMapper(BookTypeMapper bookTypeMapper){this.bookTypeMapper = bookTypeMapper;}
    @Autowired
    public void setTypeMapper(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }
    @Autowired
    public void setStockMapper(StockMapper stockMapper){this.stockMapper = stockMapper;}

    /**
     * 根据isbn号查询
     */
    @Override
    public BookVo getBookByISBN(String isbn) {
        //通过mapper层进行查询
        return bookMapper.getBookByISBN(isbn);
    }

    /**
     * 添加图书
     */
    @Override
    public int addBook(BookVo bookVo) {
        int row = 0;
        //首先存入图书,如果成功则将图书与对应的类型关系存入book_type表中
        //创建ISBN唯一标识码并判断是否唯一
        String ISBN = CreateISBNUtil.createISBN();
        BookVo isOnly = getBookByISBN(ISBN);
        while (isOnly != null){
            //编码不唯一 继续生成
            ISBN = CreateISBNUtil.createISBN();
            isOnly = getBookByISBN(ISBN);
        }
        bookVo.setIsbn(ISBN);
        bookVo.setIsDeleted(NO_DELETE.getCode());
        //获取类型id
        Type type = typeMapper.getIdByName(bookVo.getCategory());
        bookVo.setTypeId(type.getId());
        //通过mapper层增添数据
        bookMapper.addBook(bookVo);
        //由于mybaitsplus返回id有问题,通过iSBN获取id
        BookVo bookId = bookMapper.getBookByISBN(ISBN);
        bookVo.setId(bookId.getId());
        //判断是否添加成功
        BookVo isExisted = bookMapper.getBookByISBN(bookVo.getIsbn());
        if (isExisted != null){
            //添加成功,将图书与类型信息添加到book_type表中
            int tureFlag =  bookTypeMapper.addBookType(bookVo);
            if (tureFlag >0) {
                //图书信息和图书类型添加成功,将图书信息添加到库存表中
                return stockMapper.addStock(bookVo);
            }else {
                return tureFlag;
            }
        } else {
            //添加失败
            return row;
        }
    }

    /**
     * 查询图书数量
     */
    @Override
    public Integer selectTotal(BookVo bookVo) {
        return bookMapper.selectTotal(bookVo);
    }

    /**
     * 多本图书上架
     */
    @Override
    public int listBooksById(List<Book> books){
        //设置上架状态
        for(Book book : books){
            book.setIsDeleted(NO_DELETE.getCode());
            book.setUpdateTime(new Date());
        }
        //mapper层设置上架状态
        return bookMapper.listBooksById(books);
    }

    /**
     * 多本图书下架
     */
    @Override
    public int removeBooksById(List<Book> books){
        //设置下架状态
        for(Book book : books){
            book.setIsDeleted(IS_DELETE.getCode());
            book.setUpdateTime(new Date());
        }
        //mapper层设置下架状态
        return bookMapper.removeBooksById(books);
    }

    /**
     * 修改图书
     */
    @Override
    public int updateBook(BookVo bookVo) {
        int tureFlag = 1;
        int falseFlag = 0;
        //获取类型id
        if (bookVo.getCategory() != null) {
            Type type = typeMapper.getIdByName(bookVo.getCategory());
            bookVo.setTypeId(type.getId());
        }
        //修改图书
        int row = bookMapper.updateBook(bookVo);
        if (row > 0){
            //book表中数据修改成功
            if(bookVo.getTypeId() != null){
                //获取bookType数据,修改图书类型
                return bookTypeMapper.updateBookType(bookVo);
            }
            return tureFlag;
        }else {
            //修改失败
            return falseFlag;
        }
    }

    @Override
    public Page<BookVo> selectBookPage(BookVo bookVo) {
        //分页
        Page<BookVo> page = new Page<>(bookVo.getCurrent(), bookVo.getSize());
        //查询
        bookMapper.selectBookPage(page, bookVo);
        return page;
    }

    @Override
    public BookVo getBookDetailsById(Long id) {
        return bookMapper.getBookDetailsById(id);
    }

}




