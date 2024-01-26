package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.mapper.UserMapper;
import com.platform.pojo.User;
import com.platform.pojo.vo.UserVo;
import com.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.platform.common.DeleteState.IS_DELETE;
import static com.platform.common.DeleteState.NO_DELETE;

/**
 * @author wzr
 * @description 针对表【user(书城客户)】的数据库操作Service实现
 * @createDate 2024-01-14 16:56:54
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private UserMapper userMapper;
    @Autowired
    private void setUserMapper(UserMapper userMapper){this.userMapper = userMapper;}

    /**
     * (批量)注销用户
     */
    @Override
    public int removeUsersById(List<User> users) {
        //设置注销状态
        for(User user : users){
            user.setIsDeleted(IS_DELETE.getCode());
        }
        //mapper层设置注销状态
        return userMapper.removeUsersById(users);
    }

    /**
     * (批量)登记
     */
    @Override
    public int listUsersById(List<User> users) {
        //设置登记状态
        for(User user : users){
            user.setIsDeleted(NO_DELETE.getCode());
        }
        //mapper层设置登记状态
        return userMapper.listUsersById(users);
    }

    /**
     * 根据条件查询用户信息
     */
    @Override
    public Page<UserVo> selectUser(UserVo userVo) {
        //分页
        Page<UserVo> page = new Page<>(userVo.getCurrent(), userVo.getSize());
        //查询
        userMapper.selectUser(page, userVo);
        return page;
    }

    /**
     * 获取用户详情
     */
    @Override
    public UserVo getUserDetailsById(Long id) {
        return userMapper.getUserDetailsById(id);
    }

    /**
     * 查询用户数量
     */
    @Override
    public Integer selectTotal(UserVo userVo) {
        return userMapper.selectTotal(userVo);
    }

}




