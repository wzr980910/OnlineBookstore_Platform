package com.platform.controller;

import com.platform.pojo.Book;
import com.platform.pojo.vo.BookVo;
import com.platform.service.BookService;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private BookService bookService;

    //添加图书信息,获取BookVo实体类,将BookVo实体类中的数据分为Book实体类和book_type实体类存入数据库
    @PostMapping("/addBook")
    public RestResult addBook(@RequestBody BookVo bookVo){
        RestResult restResult = null;
        //将bookVo传入service层进行处理
        boolean isAdd = bookService.addBook(bookVo);
        if(isAdd){
            //添加成功,返回成功消息
            restResult = RestResult.success();
        }else{
            //添加失败
            restResult = RestResult.failure(BOOK_HAS_EXISTED);
        }
        return restResult;
    }

    //下架图书
    @PostMapping("/deleteBook")
    public RestResult deleteBook (@RequestParam String ISBN){
        RestResult restResult = null;
        //下架图书
        boolean isDeleted = bookService.deleteByISBN(ISBN);
        if (isDeleted){
            //下架成功
            restResult =  RestResult.success();
        }else {
            //下架失败
            restResult = RestResult.failure(OPERATION_FAILURE);
        }
        return restResult;
    }

    //上架图书
    /*@PostMapping("/deleteBook")
    public RestResult deleteBook (@RequestParam String ISBN){
        RestResult restResult = null;
        //下架图书
        boolean isDeleted = bookService.deleteByISBN(ISBN);
        if (isDeleted){
            //下架成功
            restResult =  RestResult.success();
        }else {
            //下架失败
            restResult = RestResult.failure(OPERATION_FAILURE);
        }
        return restResult;
    }*/

    //修改图书,获取BookVo实体类
    @PostMapping("/updateBook")
    public RestResult updateBook(@RequestBody BookVo bookVo){
        RestResult restResult = null;
        //将bookVo传入service层进行处理
        boolean isUpdate = bookService.updateBook(bookVo);
        if (isUpdate){
            //修改成功
            restResult =  RestResult.success();
        }else {
            //修改失败
            restResult = RestResult.failure(OPERATION_FAILURE);
        }
        return restResult;
    }

    //根据条件查询图书
    @PostMapping("/selectBook")
    public RestResult selectBook(@RequestBody BookVo bookVo){
        RestResult restResult=null;
        Map<String,Object> map=bookService.selectBookPage(bookVo);
        //查询成功，包装数据返回
        restResult=new RestResult(ResultCode.SUCCESS,map);
        return restResult;
    }
}
