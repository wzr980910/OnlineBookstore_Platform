package com.platform.mapper;

import com.platform.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

    void addAdmin(Admin admin);

    void deleteByAdminName(String adminName,int isDeleted,Date updateTime);

    void updateByAdminName(Admin admin);

    Admin selectAdmin(String adminName);

    List<Admin> selectAllAdmin();
}




