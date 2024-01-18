package com.platform.service;

import com.platform.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.vo.AdminVo;
import com.platform.pojo.vo.UserVo;

import java.nio.charset.CoderResult;
import java.util.Map;

/**
 * @author 邓桂材
 * @description 针对表【user(书城客户)】的数据库操作Service
 * @createDate 2024-01-14 16:56:54
 */
public interface UserService extends IService<User> {
    public User selectByAccount(String accountNumber);

    Integer insert(User user);

    User login(String username, String password);

    //删除
    boolean deleteByNumber(String accountNumber);

    //更新
    boolean updateUser(User user);

    //查询
    Map<String,Object> selectUser(UserVo userVo);
}
