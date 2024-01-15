package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.pojo.Admin;
import com.platform.service.AdminService;
import com.platform.mapper.AdminMapper;
import com.platform.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 邓桂材
 * @description 针对表【admin(管理员表)】的数据库操作Service实现
 * @createDate 2024-01-14 16:56:54
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
        implements AdminService {
    private AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl (AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin login(String adminName, String password) {
        String passwordEncry = MD5Util.encrypt(password);
        return adminMapper.selectAdmin(adminName,passwordEncry);
    }
}




