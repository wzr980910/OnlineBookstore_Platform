package com.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.vo.AdminVo;
import com.platform.pojo.vo.BookVo;
import com.platform.util.result.RestResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
* @author wzr
* @description 针对表【admin(管理员表)】的数据库操作Service
* @createDate 2024-01-14 16:56:54
*/
public interface AdminService extends IService<Admin> {

    /**
     * 校验登录操作
     */
    RestResult login(String adminName, String password);

    /**
     * 校验账号是否存在
     */
    Admin getByAdminName(String adminName);

    /**
     * 将账号添加到数据库中
     */
    int addAdmin(Admin admin);

    /**
     * 删除账号
     */
    int removeAdminById(Long id);

    /**
     * 修改管理员账号
     */
    int updateAdmin(Admin admin);

    /**
     * 条件查找账号
     */
    Page<AdminVo> selectAdmin(AdminVo adminVo);

    /**
     * 批量注销
     */
    int removeAdminsById(List<Admin> admins);

    /**
     * 批量登记
     */
    int listAdminsById(List<Admin> admins);

    /**
     * 上传头像
     */
    int uploadAdminImg(Long adminId, MultipartFile file);

    /**
     * 查询管理员数量
     */
    AdminVo selectTotal(AdminVo adminVo);
}
