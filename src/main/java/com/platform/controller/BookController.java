package com.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.Book;
import com.platform.pojo.vo.BookVo;
import com.platform.service.BookService;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.platform.util.result.ResultCode.*;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/16/15:25
 * @Description:图书操作类
 */
@Api(tags = "管理员操作")
@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;
    @Autowired
    private void setBookController(BookService bookService){this.bookService = bookService;}

    //添加图书信息,获取BookVo实体类,将BookVo实体类中的数据分为Book实体类和book_type实体类存入数据库
    @PostMapping("/addBook")
    public RestResult addBook(@RequestBody BookVo bookVo){
        //将bookVo传入service层进行处理
        boolean isAdd = bookService.addBook(bookVo);
        if(isAdd){
            //添加成功,返回成功消息
            return RestResult.success();
        }else{
            //添加失败
            return RestResult.failure(BOOK_HAS_EXISTED);
        }
    }

    //下架图书
    @PostMapping("/removeBook")
    public RestResult removeBook (@RequestParam Long id){
        //下架图书
        boolean isDeleted = bookService.removeById(id);
        if (isDeleted){
            //下架成功
            return RestResult.success();
        }else {
            //下架失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    //上架图书
    @PostMapping("/listBook")
    public RestResult listBook (@RequestParam Long id){
        //上架图书
        boolean isDeleted = bookService.listById(id);
        if (isDeleted){
            //上架成功
            return RestResult.success();
        }else {
            //上架失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    //批量上架图书
    @PostMapping("/listBooks")
    public RestResult listBooks(@RequestBody List<Book> books){
        boolean isListed = bookService.listBooksById(books);
        if (isListed){
            return RestResult.success(ResultCode.SUCCESS, "上架成功");
        }else {
            return RestResult.failure(ResultCode.OPERATION_FAILURE,"上架失败");
        }
    }

    //批量上架图书
    @PostMapping("/removeBooks")
    public RestResult removeBooks(@RequestBody List<Book> books){
        boolean isListed = bookService.removeBooksById(books);
        if (isListed){
            return RestResult.success(ResultCode.SUCCESS, "下架成功");
        }else {
            return RestResult.failure(ResultCode.OPERATION_FAILURE,"下架失败");
        }
    }

    //修改图书,获取BookVo实体类
    @PostMapping("/updateBook")
    public RestResult updateBook(@RequestBody BookVo bookVo){
        //将bookVo传入service层进行处理
        boolean isUpdate = bookService.updateBook(bookVo);
        if (isUpdate){
            //修改成功
            return RestResult.success();
        }else {
            //修改失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    //根据条件查询图书
    @PostMapping("/selectBook")
    public RestResult selectBook(@RequestBody BookVo bookVo){
        IPage<BookVo> page = bookService.selectBookPage(bookVo);
        if (page != null) {
            //查询成功，包装数据返回
            Map<String, Object> pageInfoMap = new HashMap<>();
            pageInfoMap.put("pageInfo", page);
            return RestResult.success(ResultCode.SUCCESS, "查询成功", pageInfoMap);
        } else {
            //查询失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    //图书详情
    @PostMapping("/bookDetails")
    public RestResult bookDetails(@RequestParam Long id){
        BookVo bookVo = bookService.getBookDetailsById(id);
        if (bookVo != null){
            //查询图书详情成功
            return RestResult.success(bookVo);
        }else{
            //查询图书详情失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }
}
