package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.mapper.AdminMapper;
import com.platform.pojo.Admin;
import com.platform.pojo.vo.AdminVo;
import com.platform.service.AdminService;
import com.platform.util.JwtHelper;
import com.platform.util.MD5Util;
import com.platform.util.result.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.platform.common.DeleteState.IS_DELETE;
import static com.platform.common.DeleteState.NO_DELETE;
import static com.platform.util.result.ResultCode.USER_LOGIN_ERROR;
import static com.platform.util.result.ResultCode.USER_NOT_EXIST;

/**
 * @author wzr
 * @description 针对表【admin(管理员表)】的数据库操作Service实现
 * @createDate 2024-01-14 16:56:54
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
        implements AdminService {
    private AdminMapper adminMapper;
    private JwtHelper jwtHelper;

    @Autowired
    private void setAdminServiceImpl(AdminMapper adminMapper){this.adminMapper = adminMapper;}
    @Autowired
    private void setAdminServiceImpl(JwtHelper jwtHelper){this.jwtHelper = jwtHelper;}

    /**
     * 登录操作
     */
    @Override
    public RestResult login(String adminName, String password) {
        //判断用户名是否存在
        Admin isAdminName = adminMapper.getByAdminName(adminName);
        if (isAdminName == null){
            //用户名不存在
            return RestResult.failure(USER_NOT_EXIST);
        }
        Map<String, Object> map = new HashMap<>();
        String passwordEncry = MD5Util.encrypt(password);
        Admin admin =  adminMapper.login(adminName,passwordEncry);
        if (admin != null){
            //生成token
            String token = jwtHelper.createToken(Long.valueOf(admin.getId()));
            map.put("admin", admin);
            map.put("token", token);
            return RestResult.success(map);
        }else {
            return RestResult.failure(USER_LOGIN_ERROR);
        }
    }

    /**
     * 判断管理员是否存在
     */
    @Override
    public Admin getByAdminName(String adminName) {
        return adminMapper.getByAdminName(adminName);
    }

    /**
     * 将账号添加到数据库中
     */
    @Override
    public int addAdmin(Admin admin){
        //设置删除状态
        admin.setIsDeleted(NO_DELETE.getCode());
        //密码加密
        String passwordEncry = MD5Util.encrypt(admin.getPassword());
        admin.setPassword(passwordEncry);
        //通过mapper层增添数据
        return adminMapper.addAdmin(admin);
    }

    /**
     * 修改用户信息
     */
    @Override
    public int updateAdmin(Admin admin) {
        admin.setPassword(MD5Util.encrypt(admin.getPassword()));
        //通过mapper层进行修改
        return adminMapper.updateById(admin);
    }

    /**
     * 根据条件查询
     */
    @Override
    public Page<AdminVo> selectAdmin(AdminVo adminVo) {
        //分页
        Page<AdminVo> page = new Page<>(adminVo.getCurrent(), adminVo.getSize());
        //查询
        adminMapper.selectAdmin(page, adminVo);
        return page;
    }

    /**
     * 批量注销
     */
    @Override
    public int removeAdminsById(List<Admin> admins) {
        //设置注销状态
        for(Admin admin : admins){
            admin.setIsDeleted(IS_DELETE.getCode());
            admin.setUpdateTime(new Date());
        }
        //mapper层设置注销状态
        return adminMapper.removeAdminsById(admins);
    }

    /**
     * 批量登记
     */
    @Override
    public int listAdminsById(List<Admin> admins) {
        //设置登记状态
        for(Admin admin : admins){
            admin.setIsDeleted(NO_DELETE.getCode());
            admin.setUpdateTime(new Date());
        }
        //mapper层设置登记状态
        return adminMapper.listAdminsById(admins);
    }

    /**
     * 查询用户数量
     */
    @Override
    public Integer selectTotal(AdminVo adminVo) {
        return adminMapper.selectTotal(adminVo);
    }
}