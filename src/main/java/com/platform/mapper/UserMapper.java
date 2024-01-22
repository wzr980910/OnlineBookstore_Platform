package com.platform.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.pojo.Admin;
import com.platform.pojo.Book;
import com.platform.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
* @author 邓桂材
* @description 针对表【user(书城客户)】的数据库操作Mapper
* @createDate 2024-01-14 16:56:54
* @Entity com.bookStore.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /*(批量)注销*/
    void removeUsersById(List<User> users);

    /*(批量)登记*/
    void listUsersById(List<User> users);

    /*根据条件查询用户信息*/
    IPage<Admin> selectUser(IPage<?> page, UserVo userVo);

    /*查询用户详情*/
    UserVo getUserDetailsById(Long id);
}




