package com.platform.mapper;

import com.platform.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 邓桂材
* @description 针对表【user(书城客户)】的数据库操作Mapper
* @createDate 2024-01-14 16:56:54
* @Entity com.bookStore.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




