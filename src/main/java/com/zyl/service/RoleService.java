package com.zyl.service;

import com.zyl.entity.Role;
import com.zyl.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    //根据名字查询角色
    @Autowired
    private RoleMapper roleMapper;

    public String selectRoleByUsername(String username){
        Role role = roleMapper.selectByUsername(username);
        System.out.println("role = " + role);
        if (role!=null){
            return role.getRname();
        }
        return null;
    }

}
