package com.platform.service;

import com.platform.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 邓桂材
* @description 针对表【admin(管理员表)】的数据库操作Service
* @createDate 2024-01-14 16:56:54
*/
public interface AdminService extends IService<Admin> {

    Admin login(String adminName, String password);
}
