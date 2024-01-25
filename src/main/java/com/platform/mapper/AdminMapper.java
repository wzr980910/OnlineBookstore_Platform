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
    Admin login(String adminName, String password);

    Admin getByAdminName(String adminName);

    Admin getById(Long id);

    int addAdmin(Admin admin);

    int removeById(Long id,int isDeleted,Date updateTime);

    int removeAdminsById(List<Admin> admins);

    int listAdminsById(List<Admin> admins);

    int updateById(Admin admin);

    IPage<AdminVo> selectAdmin(IPage<?> page, AdminVo adminVo);

    int updateAdminImg(@Param("imgPath") String imgPath,@Param("adminId") Long adminId);
}




