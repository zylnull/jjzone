package com.zyl.mapper;

import com.zyl.entity.Friend;
import org.springframework.stereotype.Component;

@Component
public interface FriendMapper {
    //删除好友
    int deleteByFriend(Friend friend);

    //添加好友
    int insertByFriend(Friend friend);

    //查询好友
    Friend selectFriend(Friend friend);
}
