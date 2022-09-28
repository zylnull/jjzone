package com.zyl.mapper;

import com.zyl.entity.Admin;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminMapper {
    //查询所有管理员
    List<Admin> selectAllAdmin();
    //根据用户名查询用户
    Admin selectByAdminName(String adminName);
    //注册
    int registerAdmin(Admin admin);
}
