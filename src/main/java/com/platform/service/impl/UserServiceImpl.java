package com.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.pojo.Admin;
import com.platform.pojo.User;
import com.platform.pojo.vo.UserVo;
import com.platform.service.UserService;
import com.platform.mapper.UserMapper;
import com.platform.util.JwtHelper;
import com.platform.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.CoderResult;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.platform.common.DeleteState.IS_DELETE;
import static com.platform.common.DeleteState.NO_DELETE;

/**
 * @author 邓桂材
 * @description 针对表【user(书城客户)】的数据库操作Service实现
 * @createDate 2024-01-14 16:56:54
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User selectByAccount(String accountNumber) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper();
        wrapper.eq(User::getAccountNumber, accountNumber);
        String sql = wrapper.getSqlSegment();
        System.out.println(sql);
        User userTemp = userMapper.selectOne(wrapper);
        return userTemp;
    }

    @Override
    public Integer insert(User user) {
        String passwordEncrypt = MD5Util.encrypt(user.getPassword());
        user.setPassword(passwordEncrypt);
        int insert = userMapper.insert(user);
        return insert;
    }

    @Override
    public User login(String accountNumber, String password) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccountNumber, accountNumber);
        //加密密码
        String passwordEncry = MD5Util.encrypt(password);
        wrapper.eq(User::getPassword, passwordEncry);
        //通过用户名和加密后的密码查找用户
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public boolean deleteByNumber(String accountNumber) {
        //设置删除状态
        int isDeleted = NO_DELETE.getCode();
        //通过mapper层进行删除
        userMapper.deleteByNumber(accountNumber,isDeleted,new Date());
        //判断是否删除
        User isDelete = userMapper.getByNumber(accountNumber);
        if (isDelete.getIsDeleted().equals(IS_DELETE.getCode())){
            //删除成功
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        //新密码进行加密
        String passwordEncy = MD5Util.encrypt(user.getPassword());
        user.setPassword(passwordEncy);
        userMapper.updateUser(user);
        //修改成功
        return true;
    }

    @Override
    public Map<String, Object> selectUser(UserVo userVo) {
        //分页
        Page<Admin> page = new Page<>(userVo.getPageNum(), userVo.getPageSize());
        //查询
        userMapper.selectUser(page, userVo);
        //封装查询到的内容
        Map<String, Object> pageInfo = new HashMap<>();
        //从page中获得返回的数据，作为value放入map中，对应k值为pageData
        pageInfo.put("pageData", page.getRecords());
        //从page中返回当前是第几页
        pageInfo.put("pageNum", page.getCurrent());
        //从page中返回当前页容量
        pageInfo.put("pageSize", page.getSize());
        //返回总页数
        pageInfo.put("totalPage", page.getPages());
        //返回结果总条数
        pageInfo.put("totalSize", page.getTotal());
        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo", pageInfo);
        return pageInfoMap;
    }


}




