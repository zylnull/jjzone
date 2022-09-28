package com.zyl.mapper;

import com.zyl.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    //添加用户
    int insertUser(User user);

    //使用用户名查询用户
    User selectByUsername(String username);

    //查询所有用户
    List<User> selectAllUser();

    //查询好友列表
    List<User> selectUserFriends(String username);

    //根据用户名或名称查询用户
    List<User> selectUserFromKeyword(String keyword);

    //根据用户id删除用户
    int deleteByUid(Integer uid);

    //修改用户信息
    int updateByUid(User user);

}
