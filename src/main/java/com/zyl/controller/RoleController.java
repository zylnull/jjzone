package com.zyl.controller;

import com.zyl.entity.Role;
import com.zyl.http.R;
import com.zyl.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public R selectRoleByUsername(@RequestBody String username){
        username = username.substring(0,username.length()-1);
        System.out.println("username = " + username);
        String role = roleService.selectRoleByUsername(username);
        if (role!=null){
            return R.ok(200,"查询成功！",role);
        }
        return R.error(400,"查询失败！");
    }
}
