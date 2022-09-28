package com.zyl.controller;

import com.zyl.entity.*;
import com.zyl.http.R;
import com.zyl.mapper.PowerMapper;
import com.zyl.service.AdminService;
import com.zyl.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/test")
    public String testAdmin(){
        List<Admin> admins = adminService.selectAllAdmin();
//        System.out.println("admins = " + admins);
        return "成功！";
    }
    @PostMapping("/login")
    public R login(@RequestBody Admin admin){
        UserVo vo = adminService.loginToke(admin);
        if (vo!=null){
            return R.ok(200,"登录成功",vo);
        }
        return R.error(400,"登录失败！");
    }
    @PostMapping("/register")
    public R register(@RequestBody Admin admin){
        String s = adminService.registerAdmin(admin);
        if (s!=null){
            return R.ok(200,s,s);
        }
        return R.error(400,s);
    }
    @PostMapping("/menu")
    public R selectAdminMenu(@RequestBody String username){
        List<Power> menu = adminService.selectMenuByUsername(username);
        if (menu!=null){
            return R.ok(200,"查询成功！",menu);
        }
        return R.error(400,"查询失败");
    }

    //查询所有用户
    @PostMapping("/userList")
    public R  selectAllUser(){
        List<User> users = adminService.selectAllUserByAdmin();
        if (users != null){
            return R.ok(200,"查询成功！",users);
        }
        return R.error(400,"查询无结果！");
    }
    //根据用户uid删除用户
    @PostMapping("/deleteUser")
    public R deleteByUid(@RequestBody String uid){
        uid = uid.substring(0,uid.length()-1);
        System.out.println("uid = " + Integer.parseInt(uid));
        int i = adminService.deleteByUid(Integer.parseInt(uid));
        if (i > 0){
            return R.ok(200,"删除成功！",i);
        }
        return R.error(400,"删除失败！");
    }

    //查询日志
    @PostMapping("/log")
    public R selectAllLog(){
        List<Log> logs = adminService.selectAllLog();
        if (logs!=null){
            return R.ok(200,"查询成功！",logs);
        }
        return R.error("查询失败！");
    }

    //查询所有留言
    @PostMapping("/loadnotices")
    public R selectAllNotice(){
        List<Guestbook> guestbooks = adminService.selectAllGuestbookByAdmin();
        if (guestbooks!=null){
            return R.ok(200,"加载成功！",guestbooks);
        }
        return R.error(400,"加载失败！");
    }

    //更具更新状态值
    @PostMapping("/changeStatus")
    public R updateStatus(@RequestBody Guestbook guestbook){
        int i = adminService.updateStatus(guestbook);
        if (i>0){
            return R.ok(200,"修改成功！",i);
        }
        return R.error(400,"修改失败！");
    }

    //查询所有公告！
    @GetMapping("/notices")
    public R selectAllNotices(){
        List<Notice> notices = adminService.selectAllNotice();
        if (notices!=null){
            return R.ok(200,"查询成功！",notices);
        }
        return R.error(400,"查询为空！");
    }
    //添加公告
    @PostMapping("/addNotice")
    public R addNotice(@RequestBody Notice notice){
        System.out.println("notice = " + notice);
        int i = adminService.addNotice(notice);
        if (i==1){
            return R.ok(200,"成功！",i);
        }
        return R.error(400,"失败！");
    }
    //删除公告！
    @GetMapping("/delectNotice/{id}")
    public R delNotice(@PathVariable String id){
        int i = adminService.deleteNotice(id);
        if (i>0){
            return R.ok(200,"删除成功！",i);
        }
        return R.error(400,"删除失败！");
    }
    //修改公告
    @PostMapping("/updateNotice")
    public R updateNotice(@RequestBody Notice notice){
        int i = adminService.updateNotice(notice);
        if (i==1){
            return R.ok(200,"公告修改成功！",i);
        }
        return R.error("公告修改失败！");
    }

}
