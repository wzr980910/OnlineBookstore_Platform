package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.pojo.Admin;
import com.platform.pojo.Book;
import com.platform.pojo.vo.AdminVo;
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

import static com.platform.common.DeleteState.IS_DELETE;
import static com.platform.common.DeleteState.NO_DELETE;
import static com.platform.util.result.ResultCode.*;

/**
 * @author wzr
 * @description 针对表【admin(管理员表)】的数据库操作Service实现
 * @createDate 2024-01-14 16:56:54
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
        implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private JwtHelper jwtHelper;

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
        admin.setIsDeleted(NO_DELETE.getCode());
        //密码加密
        String passwordEncry = MD5Util.encrypt(admin.getPassword());
        admin.setPassword(passwordEncry);
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
        int isDeleted = NO_DELETE.getCode();
        //通过mapper层进行删除
        adminMapper.deleteByAdminName(adminName,isDeleted,new Date());
        //判断是否删除
        Admin isDelete = adminMapper.getByAdminName(adminName);
        if (isDelete.getIsDeleted().equals(IS_DELETE.getCode())){
            //删除成功
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        admin.setPassword(MD5Util.encrypt(admin.getPassword()));
        //通过mapper层进行修改
        adminMapper.updateByAdminName(admin);
        return true;
    }

    //根据条件查询
    @Override
    public Map<String, Object> selectAdmin(AdminVo adminVo) {
        //分页
        Page<Admin> page = new Page<>(adminVo.getPageNum(), adminVo.getPageSize());
        //查询
        adminMapper.selectAdmin(page, adminVo);
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




