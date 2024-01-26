package com.platform.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.Book;
import com.platform.pojo.vo.AdminVo;
import com.platform.pojo.vo.BookVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author wzr
* @description 针对表【admin(管理员表)】的数据库操作Mapper
* @createDate 2024-01-14 16:56:54
* @Entity com.bookStore.pojo.Admin
*/
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 判断用户登录
     */
    Admin login(String adminName, String password);

    /**
     * 根据用户名查询账号
     */
    Admin getByAdminName(String adminName);

    /**
     * 添加用户
     */
    int addAdmin(Admin admin);

    /**
     * 注销用户
     */
    int removeById(Long id,int isDeleted,Date updateTime);

    /**
     * 注销用户
     */
    int removeAdminsById(List<Admin> admins);

    int listAdminsById(List<Admin> admins);

    int updateById(Admin admin);

    IPage<AdminVo> selectAdmin(IPage<?> page, AdminVo adminVo);

    int updateAdminImg(@Param("imgPath") String imgPath,@Param("adminId") Long adminId);

    AdminVo selectTotal(@Param("admin") AdminVo adminVo);
}




