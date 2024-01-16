package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.pojo.Admin;
import com.platform.service.AdminService;
import com.platform.mapper.AdminMapper;
import com.platform.util.JwtHelper;
import com.platform.util.MD5Util;
import com.platform.util.result.RestResult;
import com.platform.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.platform.util.result.ResultCode.*;

/**
 * @author wzr
 * @description 针对表【admin(管理员表)】的数据库操作Service实现
 * @createDate 2024-01-14 16:56:54
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
        implements AdminService {

    //定义删除状态
    public static final int NO_DELETE = 1;
    public static final int IS_DELETE = 2;
    private AdminMapper adminMapper;
    private JwtHelper jwtHelper;

    @Autowired
    public AdminServiceImpl (AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Autowired
    public void setJwtHelper(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    //登录操作
    @Override
    public RestResult login(String adminName, String password) {
        Map<String, Object> map = new HashMap<>();
        String passwordEncry = MD5Util.encrypt(password);
        Admin admin =  adminMapper.login(adminName,passwordEncry);
        RestResult restResult = null;
        if (admin != null){
            //生成token
            String token = jwtHelper.createToken(Long.valueOf(admin.getId()));
            map.put("admin", admin);
            map.put("token", token);
            restResult = RestResult.success(map);
        }else {
            restResult = RestResult.failure(USER_LOGIN_ERROR);
        }
        return restResult;
    }

    //判断管理员是否存在
    @Override
    public Admin getByAdminName(String adminName) {
        return adminMapper.getByAdminName(adminName);
    }


    //将账号添加到数据库中
    @Override
    public boolean addAdmin(Admin admin){
        //设置删除状态
        admin.setIsDeleted(NO_DELETE);
        //密码加密
        String passwordEncry = MD5Util.encrypt(admin.getPassword());
        admin.setPassword(passwordEncry);
        //添加创建时间
        admin.setCreateTime(new Date());
        //通过mapper层增添数据
        adminMapper.addAdmin(admin);
        //判断是否添加成功
        Admin isAdmin = adminMapper.getByAdminName(admin.getAdminName());
        if (isAdmin != null){
            //添加成功
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAdmin(String adminName) {
        //设置删除状态
        int isDeleted = IS_DELETE;
        //通过mapper层进行删除
        adminMapper.deleteByAdminName(adminName,isDeleted,new Date());
        //判断是否删除
        Admin isDelete = adminMapper.getByAdminName(adminName);
        if (isDelete.getIsDeleted().equals(IS_DELETE)){
            //删除成功
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        //添加更新时间
        admin.setUpdateTime(new Date());
        //通过mapper层进行修改
        adminMapper.updateByAdminName(admin);
        return true;
    }

    @Override
    public Admin selectAdmin(String adminName) {
        //通过mapper层进行查询
        return adminMapper.selectAdmin(adminName);
    }

    @Override
    public List<Admin> selectAllAdmin() {
        return adminMapper.selectAllAdmin();
    }
}




