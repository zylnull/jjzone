package com.zyl.controller;


import com.zyl.entity.Friend;
import com.zyl.entity.Guestbook;
import com.zyl.entity.Power;
import com.zyl.entity.User;
import com.zyl.http.R;
import com.zyl.service.UserService;

import com.zyl.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public R userRegister(@RequestBody User user){
        String s = userService.registerUser(user);
        if (s!=null){
            return R.ok(200,s,s);
        }
        return R.error(400,s);
    }


    @PostMapping("/login")
    private R UserLogin(@RequestBody User user){
        UserVo vo = userService.userLogin(user);
        if (vo!=null){
            return R.ok(200,"登录成功！",vo);
        }
        return R.error("登陆失败！");
        }

    @PostMapping("/menu")
    public R userMenu(@RequestBody String username){
        List<Power> powerList = userService.selectMenu(username);
        if (powerList!=null){
            return R.ok(200,"查询成功！",powerList);
        }
        return R.error(500,"查询失败！");
    }
    @PostMapping("/friendList")
    public R userFriends(@RequestBody String username){
        List<User> users = userService.selectFriend(username);
        if (users != null){
            return R.ok(200,"查询成功！",users);
        }
        return R.error(400,"查询失败！");
    }

    @PostMapping("/nowUser")
    public R NowUser(@RequestBody String username){
        User user = userService.selectUserByFriend(username);
        if (user!=null){
            return R.ok(200,"当前用户",user);
        }
        return R.error(400,"查询失败！");
    }

    @PostMapping("/deleteFriend")
    public R deleteFriend(@RequestBody Friend friend){
        System.out.println("friend = " + friend);
        Integer delete = userService.deleteFromNowUser(friend);
        if (delete>0){
            return R.ok("删除成功！");
        }
        return R.error("删除失败！");
    }

    @PostMapping("/findFriend")
    public R selectByKeywordWithFriend(@RequestBody String keyword){
        List<User> users = userService.selectByKeyword(keyword);
        if (users!=null){
            return R.ok(200,"查询成功！",users);
        }
        return R.error(400,"查询失败！");
    }

    @PostMapping("/isFriendWithMe")
    public R isExistFriend(@RequestBody Friend friend){
        System.out.println("friend = " + friend);
        Friend select = userService.selectByFriend(friend);
        if (select!=null){
            return R.ok(200,"查询成功！",select);
        }
        return R.error(500,"查询失败！");
    }

    @PostMapping("/addFriend")
    public R addFriend(@RequestBody Friend friend){
        boolean b = userService.addFriend(friend);
        if (b){
            return R.ok(200,"添加成功！",null);
        }
        return R.error("添加失败！");
    }

    @GetMapping("/currnetGuestBook/{uid}")
    public R currentGuestBook(@PathVariable String uid){
        List<Guestbook> guestbooks = userService.selectAllGuestbookWithMe(uid);
        if (guestbooks != null){
            return R.ok(200,"查询成功！",guestbooks);
        }
        return R.error(400,"查询失败！");
    }
    //查询本人发布过的留言
    @GetMapping("/currnetSendGuestBook/{gid}")
    public R currentSendGuestBook(@PathVariable String gid){
        List<Guestbook> guestbooks = userService.selectAllGuestbookWithMyself(gid);
        if (guestbooks != null){
            return R.ok(200,"查询成功！",guestbooks);
        }
        return R.error(400,"查询失败！");
    }
    //添加留言
    @PostMapping("/insertGuestbook")
    public R addGuestbook(@RequestBody Guestbook guestbook){
        System.out.println("guestbook = " + guestbook);
        int i = userService.insertGuestbook(guestbook);
        if (i>0){
            return R.ok("添加成功！",i);
        }
        return R.error(400,"添加未成功！");
    }
    //根据id删除留言
    @GetMapping("/deleteSelfBoard/{id}")
    public R deleteGuestbook(@PathVariable String id){
        int i = userService.deleteGuestbookById(id);
        if (i>0){
            return R.ok(200,"删除成功！",i);
        }
        return R.error(400,"删除失败！");
    }
    //修改用户信息
    @PostMapping("/updateUser")
    public R updateUser(@RequestBody User user){
        int i = userService.updateUser(user);
        if (i == 1){
            return R.ok(200,"修改成功！",i);
        }
        return R.error(400,"修改失败！");
    }


}


