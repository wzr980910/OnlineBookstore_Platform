package com.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.pojo.Admin;
import com.platform.pojo.vo.AdminVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 添加管理员
     */
    int addAdmin(Admin admin);



    /**
     * 注销管理员
     */
    int removeAdminsById(List<Admin> admins);

    /**
     * 登记管理员
     */
    int listAdminsById(List<Admin> admins);

    /**
     * 修改管理管理员信息
     */
    int updateById(Admin admin);

    /**
     * 根据条件查询管理员
     */
    IPage<AdminVo> selectAdmin(IPage<?> page, AdminVo adminVo);

    /**
     * 修改管理员头像
     */
    int updateAdminImg(@Param("imgPath") String imgPath,@Param("adminId") Long adminId);

    /**
     * 查询管理员数量
     */
    Integer selectTotal(@Param("admin") AdminVo adminVo);
}




