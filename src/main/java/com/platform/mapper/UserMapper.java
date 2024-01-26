package com.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.User;
import com.platform.pojo.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 邓桂材
* @description 针对表【user(书城客户)】的数据库操作Mapper
* @createDate 2024-01-14 16:56:54
* @Entity com.bookStore.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * (批量)注销
     */
    int removeUsersById(List<User> users);

    /**
     * (批量)登记
     */
    int listUsersById(List<User> users);

    /**
     * 根据条件查询用户信息
     */
    IPage<UserVo> selectUser(IPage<?> page, @Param("userVo") UserVo userVo);

    /**
     * 查询用户详情
     */
    UserVo getUserDetailsById(Long id);

    /**
     * 查询用户数量
     */
    Integer selectTotal(@Param("userVo") UserVo userVo);
}




