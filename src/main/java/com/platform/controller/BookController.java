package com.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.Book;
import com.platform.pojo.vo.BookVo;
import com.platform.service.BookService;
import com.platform.util.DateConversionUtil;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.platform.common.BookTypeEnum.*;
import static com.platform.util.result.ResultCode.*;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/16/15:25
 * @Description:图书操作类
 */
@Api(tags = "图书操作")
@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiOperation(value = "添加图书信息", notes = "获取BookVo实体类,将BookVo实体类中的数据分为Book实体类和book_type实体类存入数据库")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
            @ApiResponse(code = 2005,message = "管理员已存在")
    })
    @PostMapping("/addBook")
    public RestResult addBook(@RequestBody BookVo bookVo){
        //获取图书类型
        String[] parts = bookVo.getCategory().split("/");
        bookVo.setCategory(parts[SECOND.getCode()]);
        //处理日期格式
        String date = DateConversionUtil.dateConversion(bookVo.getPublishDate());
        bookVo.setPublishDate(date);
        //将bookVo传入service层进行处理
        int row = bookService.addBook(bookVo);
        if(row > 0){
            //添加成功,返回成功消息
            return RestResult.success();
        }else{
            //添加失败
            return RestResult.failure(BOOK_HAS_EXISTED);
        }
    }

    @ApiOperation(value = "上架图书", notes = "传参类型为List<Book>实体类列表")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/listBooks")
    public RestResult listBooks(@RequestBody List<Book> books){
        int row = bookService.listBooksById(books);
        if (row > 0){
            return RestResult.success(ResultCode.SUCCESS, "上架成功");
        }else {
            return RestResult.failure(ResultCode.OPERATION_FAILURE,"上架失败");
        }
    }

    @ApiOperation(value = "下架图书", notes = "传参类型为List<Book>实体类列表")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/removeBooks")
    public RestResult removeBooks(@RequestBody List<Book> books){
        int row = bookService.removeBooksById(books);
        if (row > 0){
            return RestResult.success(ResultCode.SUCCESS, "下架成功");
        }else {
            return RestResult.failure(ResultCode.OPERATION_FAILURE,"下架失败");
        }
    }

    @ApiOperation(value = "修改图书", notes = "传参类型为BookVo实体类")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/updateBook")
    public RestResult updateBook(@RequestBody BookVo bookVo){
        //判断类型是否为空
        if (bookVo.getCategory() != null){
            String[] parts = bookVo.getCategory().split("/");
            bookVo.setCategory(parts[SECOND.getCode()]);
        }
        //将bookVo传入service层进行处理
        int row = bookService.updateBook(bookVo);
        if (row > 0){
            //修改成功
            return RestResult.success();
        }else {
            //修改失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    @ApiOperation(value = "查询图书", notes = "传参类型为BookVo实体类")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/selectBook")
    public RestResult selectBook(@RequestBody BookVo bookVo){
        IPage<BookVo> page = bookService.selectBookPage(bookVo);
        BookVo bookTotal = bookService.selectTotal(bookVo);
        //根据条件查询图书数量
        if (page != null) {
            //查询成功，包装数据返回
            Map<String, Object> pageInfoMap = new HashMap<>();
            pageInfoMap.put("pageInfo", page);
            pageInfoMap.put("total", bookTotal.getTotal());
            return RestResult.success(ResultCode.SUCCESS, "查询成功", pageInfoMap);
        } else {
            //查询失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    @ApiOperation(value = "图书详情信息", notes = "传参类型为id")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @GetMapping("/bookDetails")
    public RestResult bookDetails(@RequestParam(value = "id") Long id){
        BookVo bookVo = bookService.getBookDetailsById(id);
        if (bookVo != null){
            //查询图书详情成功
            return RestResult.success(bookVo);
        }else{
            //查询图书详情失败
            return RestResult.failure(OPERATION_FAILURE);
        }
    }

    @ApiOperation(value = "上传图书封面", notes = "传入格式为文件file")
    @ApiResponses({
            @ApiResponse(code=200,message = "操作成功"),
            @ApiResponse(code = 101,message = "操作失败"),
    })
    @PostMapping("/uploadBookImg")
    public RestResult uploadBookImg(@RequestParam(value = "file") MultipartFile file){
        String url =  bookService.uploadBookImg(file);
        if (url != null){
            return RestResult.success(SUCCESS,"上传成功",url);
        }else {
            return RestResult.failure(OPERATION_FAILURE,"添加封面失败");
        }
    }
}
