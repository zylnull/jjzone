package com.zyl.service;
import cn.hutool.core.date.DateUtil;
import com.zyl.annotation.SystemControllerLog;
import com.zyl.entity.*;
import com.zyl.expection.DeleteException;
import com.zyl.expection.insertException;
import com.zyl.jwt.JwtUtil;
import com.zyl.mapper.*;
import com.zyl.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{


    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PowerMapper powerMapper;
    //查询所有管理员
    @Override
    public List<Admin> selectAllAdmin() {
        return adminMapper.selectAllAdmin();
    }

    @Autowired
    private PasswordEncoder passwordEncoder; //注入验证密码的方法
    //登录方法
    @Override
    public UserVo loginToke(Admin admin) {
        //根据管理员名查询管理员
        System.out.println("admin = " + admin);
        Admin currentAdmin = adminMapper.selectByAdminName(admin.getUsername()); // 当前管理员
        if (currentAdmin==null){
            throw new UsernameNotFoundException("用户名未找到");
        }
        if (!passwordEncoder.matches(admin.getPassword(),currentAdmin.getPassword())){
            throw new BadCredentialsException("密码验证错误");
        }
        UserVo adminVo = new UserVo();
        adminVo.setToken(JwtUtil.generate(admin.getUsername()));
        adminVo.setUsername(admin.getUsername());
        adminVo.setRid(currentAdmin.getRid());
        adminVo.setUid(currentAdmin.getAid());
        return adminVo;
    }
    //注册
    @Override
    public String registerAdmin(Admin admin) {
        System.out.println("admin = " + admin);
        Admin selectByAdminName = adminMapper.selectByAdminName(admin.getUsername()); // 根据用户名查询是否存在
        if (selectByAdminName==null){ //如果不存在就存入数据库
            String encode = passwordEncoder.encode(admin.getPassword());
            Admin currentAdmin = new Admin(admin.getUsername(),encode);
            int i = adminMapper.registerAdmin(currentAdmin);
            if (i>0){
                return "注册成功！";
            }
            return "注册失败！";
        }else { //否则 返回用户名已存在
            return "用户名已存在";
        }

    }
//查询管理员菜单
    @Override
    public List<Power> selectMenuByUsername(String username) {
        username = username.substring(0,username.length()-1);
        System.out.println("username = " + username);
        List<Power> powersByUsername = powerMapper.selectPowersByAdminName(username);
        System.out.println("powersByUsername = " + powersByUsername);
        return powersByUsername;
    }

    //查询所有用户
    @Override
    public List<User> selectAllUserByAdmin() {
        return userMapper.selectAllUser();
    }
    @Autowired
    private LogMapper logMapper;

    //查询日志
    @Override
    public List<Log> selectAllLog(){
        List<Log> logs = logMapper.selectAllLog();
        return logs;
    }
    //管理员删除用户
    @SystemControllerLog(descrption = "管理员删除用户")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteByUid(Integer uid) {
        int i = userMapper.deleteByUid(uid);
        if (i >1){
            try {
                throw new DeleteException("删除异常！");
            } catch (DeleteException e) {
                e.printStackTrace();
            }
        }
        if (i==1){
            return i;
        }
        return 0;

    }
    @Autowired
    private GuestbookMapper guestbookMapper;
    //查询所有留言
    @Override
    public List<Guestbook> selectAllGuestbookByAdmin() {
        return guestbookMapper.selectAllGuestBook();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateStatus(Guestbook guestbook) {
        System.out.println("guestbook = " + guestbook);
        int i = guestbookMapper.updateStatus(guestbook);
        if (i>1){
            try {
                throw new insertException("修改异常！");
            } catch (insertException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> selectAllNotice() {
        List<Notice> notices = noticeMapper.selectAllNotice();
        if (notices!=null){
            return notices;
        }
        return null;
    }

    @SystemControllerLog(descrption = "添加公告")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addNotice(Notice notice) {
        notice.setSendTime(DateUtil.formatDateTime(new Date()));
        int i = noticeMapper.insertNotice(notice);
        if (i>1){
            try {
                throw new insertException("添加异常！");
            } catch (insertException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    @SystemControllerLog(descrption = "删除公告")
    @Override
    public int deleteNotice(String id) {
        int i = noticeMapper.deleteById(id);
        return i;
    }
    @SystemControllerLog(descrption = "更新公告")
    @Override
    public int updateNotice(Notice notice) {
        int i = noticeMapper.updateNotice(notice);
        if (i>1){
            System.out.println("\"修改大错误\" = " + "修改大错误");
        }
        return i;
    }


}
