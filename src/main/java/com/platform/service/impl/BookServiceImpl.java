package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.pojo.Book;
import com.platform.service.BookService;
import com.platform.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
* @author 邓桂材
* @description 针对表【book(图书表)】的数据库操作Service实现
* @createDate 2024-01-14 16:56:54
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{

}




