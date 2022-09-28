package com.zyl.service;

import com.zyl.entity.*;
import com.zyl.vo.UserVo;

import java.util.List;

public interface AdminService {

    List<Admin> selectAllAdmin();
    //登录
    UserVo loginToke(Admin admin);
    //注册
    String registerAdmin(Admin admin);

    //查询管理员菜单
    List<Power> selectMenuByUsername(String username);

     List<User> selectAllUserByAdmin();

     List<Log> selectAllLog();

     //管理员删除用户
    int deleteByUid(Integer uid);

    //查询所有留言板
    List<Guestbook> selectAllGuestbookByAdmin();

    //修改状态
    int updateStatus(Guestbook guestbook);

    //查询所有公告
    List<Notice> selectAllNotice();

    //添加公告！
    int addNotice(Notice notice);

    //删除公告！
    int deleteNotice(String id);

    //修改公告
    int updateNotice(Notice notice);

}
