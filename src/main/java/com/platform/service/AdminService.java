package com.platform.service;

import com.platform.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.util.result.RestResult;

import java.util.List;

/**
* @author wzr
* @description 针对表【admin(管理员表)】的数据库操作Service
* @createDate 2024-01-14 16:56:54
*/
public interface AdminService extends IService<Admin> {

    //校验登录操作
    RestResult login(String adminName, String password);

    //校验账号是否存在
    Admin getByAdminName(String adminName);

    //将账号添加到数据库中
    boolean addAdmin(Admin admin);

    //删除账号
    boolean deleteAdmin(String adminName);

    //修改管理员账号
    boolean updateAdmin(Admin admin);

    //查找单个账号
    Admin selectAdmin(String adminName);

    //查找全部账号
    List<Admin> selectAllAdmin();
}
