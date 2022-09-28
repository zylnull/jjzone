package com.zyl.service;


import cn.hutool.core.date.DateUtil;
import com.zyl.annotation.SystemControllerLog;
import com.zyl.entity.Friend;
import com.zyl.entity.Guestbook;
import com.zyl.entity.Power;
import com.zyl.entity.User;
import com.zyl.expection.DeleteException;
import com.zyl.expection.insertException;
import com.zyl.jwt.JwtUtil;
import com.zyl.mapper.FriendMapper;
import com.zyl.mapper.GuestbookMapper;
import com.zyl.mapper.PowerMapper;
import com.zyl.mapper.UserMapper;
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
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @SystemControllerLog(descrption = "用户注册")
    public String registerUser(User user){
        String encode = passwordEncoder.encode(user.getPassword());
        User currentUser = new User(user.getUsername(),encode);
        int i = userMapper.insertUser(currentUser);
        if (i>0){
            return "注册成功！";
        }
        return "注册失败！";
    }


//    @SystemControllerLog(descrption = "用户登录")
    public UserVo userLogin(User user){
        User currentUser = userMapper.selectByUsername(user.getUsername()); // 查询到的数据库用户
        System.out.println("user = " + user);
        System.out.println("currentUser = " + currentUser);
        if (currentUser==null){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        if (!passwordEncoder.matches(user.getPassword(),currentUser.getPassword())){
            throw new BadCredentialsException("密码校验失败！");
        }
        UserVo vo = new UserVo();
        vo.setUsername(user.getUsername());
        String token = JwtUtil.generate(user.getUsername());
        vo.setToken(token);
        vo.setRid(currentUser.getRid());
        vo.setUid(currentUser.getUid());
        return vo;//返回一个用用户名加密的token
    }
    @Autowired
    private PowerMapper powerMapper;

    //根据用户名查询用户菜单
    public List<Power> selectMenu(String username){
         username = username.substring(0,username.length()-1);
        System.out.println("username = " + username);
        List<Power> powerList = powerMapper.selectPowersByUsername(username);
        System.out.println("powerList = " + powerList);
        return powerList;
    }
    //查询好友列表
    public  List<User> selectFriend(String username){
        username = username.substring(0,username.length()-1);
        System.out.println("username = " + username);
        List<User> users = userMapper.selectUserFriends(username);
        System.out.println("users = " + users);
        return users;
    }

    //根据用户名查询用户(好友查询详细信息)
    public User selectUserByFriend(String username){
        username = username.substring(0, username.length() - 1);
        System.out.println("username = " + username);
        User user = userMapper.selectByUsername(username);
        System.out.println("user = " + user);
        return user;
    }

    @Autowired
    private FriendMapper friendMapper;
    //根据前端传入过来的对象删除好友

    //当出现Exception时，进行事务回滚
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteFromNowUser(Friend friend) {
        int i = friendMapper.deleteByFriend(friend);
        //如果删除数据超过一个就抛出异常进行事务回滚
        if (i>1){
            try {
                throw new DeleteException("删除异常!");
            } catch (DeleteException e) {
                e.printStackTrace();
            }
        }
        if (i==0){
            return 1;
        }
       return 0;

    }
    //根据条件查询好友
    public List<User> selectByKeyword(String keyword){
        keyword = keyword.substring(0,keyword.length()-1);
        System.out.println("keyword = " + keyword);
        List<User> users = userMapper.selectUserFromKeyword(keyword);
        System.out.println("users = " + users);
        return users;
    }

    //查询好友
    public Friend selectByFriend(Friend friend){
        Friend selectFriend = friendMapper.selectFriend(friend);
        System.out.println("selectFriend = " + selectFriend);
        return selectFriend;
    }

    //添加好友
    //当出现Exception时，进行事务回滚
    @Transactional(rollbackFor = Exception.class)
    public boolean addFriend(Friend friend){
        int insert = friendMapper.insertByFriend(friend);
        if (insert>1){
            try {
                throw new insertException("添加失败！");
            } catch (insertException e) {
                e.printStackTrace();
            }
        }
        if (insert == 1){
            return true;
        }
        return false;
    }

    @Autowired
    private GuestbookMapper guestbookMapper;

    //查询用户查询留言信息
    public List<Guestbook> selectAllGuestbookWithMe(String uid){
        System.out.println("uid = " + uid);
        List<Guestbook> guestbooks = guestbookMapper.selectAllGuestBookByCurrentId(uid);
        System.out.println("guestbooks = " + guestbooks);
        return guestbooks;
    }
    //查询本人发布过的留言
    public List<Guestbook> selectAllGuestbookWithMyself(String gid){
        System.out.println("uid = " + gid);
        List<Guestbook> guestbooks = guestbookMapper.selectAllGuestBookByOtherId(gid);
        System.out.println("guestbooks = " + guestbooks);
        return guestbooks;
    }


    //添加留言
    @SystemControllerLog(descrption = "用户添加留言")
    @Transactional(rollbackFor = Exception.class)
    public int insertGuestbook(Guestbook guestbook){
        guestbook.setSendTime(DateUtil.formatDateTime(new Date()));
        System.out.println("guestbook = " + guestbook);
        int i = guestbookMapper.insertGuestBook(guestbook);
        if (i>1){
            try {
                throw new insertException("添加失败！");
            } catch (insertException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
    //删除留言
    @SystemControllerLog(descrption = "用户删除留言")
    @Transactional(rollbackFor = Exception.class)
    public int deleteGuestbookById(String id){
        int i = guestbookMapper.deleteGuestbookById(id);
        if (i>1){
            try {
                throw new DeleteException("删除失败！");
            } catch (DeleteException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
    //修改用户信息
    @SystemControllerLog(descrption = "用户修改了信息")
    public int updateUser(User user){
        int update = userMapper.updateByUid(user);
        if (update > 1){
            System.out.println("\"修改异常了\" = " + "修改异常了");
        }
        return update;
    }



}
