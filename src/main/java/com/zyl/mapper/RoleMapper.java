package com.zyl.mapper;

import com.zyl.entity.Role;
import org.springframework.stereotype.Component;

@Component
public interface RoleMapper {
    //
    Role selectByUsername(String username);
}
