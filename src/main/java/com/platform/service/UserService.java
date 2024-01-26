package com.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.vo.AdminVo;
import com.platform.pojo.vo.UserVo;
import org.springframework.stereotype.Service;

import java.nio.charset.CoderResult;
import java.util.List;
import java.util.Map;

/**
 * @author 邓桂材
 * @description 针对表【user(书城客户)】的数据库操作Service
 * @createDate 2024-01-14 16:56:54
 */
@Service
public interface UserService extends IService<User> {

    /**
     * (批量)注销
     */
    int removeUsersById(List<User> users);

    /**
     * (批量)登记
     */
    int listUsersById(List<User> users);

    /**
     * 条件查询用户
     */
    Page<UserVo> selectUser(UserVo userVo);

    /**
     * 查询特定用户详情信息
     */
    UserVo getUserDetailsById(Long id);

    /**
     * 根据条件查询用户数量
     */
    UserVo selectTotal(UserVo userVo);
}
