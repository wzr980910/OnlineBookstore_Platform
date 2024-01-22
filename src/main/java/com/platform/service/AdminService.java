package com.platform.service;

import com.platform.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.vo.AdminVo;
import com.platform.util.result.RestResult;

import java.util.List;
import java.util.Map;

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
    boolean removeAdminById(Long id);

    //修改管理员账号
    boolean updateAdmin(Admin admin);

    //条件查找账号
    Map<String,Object> selectAdmin(AdminVo adminVo);

    //批量注销
    boolean removeAdminsById(List<Admin> admins);

    //批量登记
    boolean listAdminsById(List<Admin> admins);

}
