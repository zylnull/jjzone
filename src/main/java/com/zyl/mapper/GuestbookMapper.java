package com.zyl.mapper;

import com.zyl.entity.Guestbook;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GuestbookMapper {
    //管理员查询所有留言
    List<Guestbook> selectAllGuestBook();

    //根据当前用户查询所有留言
    List<Guestbook> selectAllGuestBookByCurrentId(String uid);

    //其他用户根据自己的id查询自己留言的所有信息
    List<Guestbook> selectAllGuestBookByOtherId(String gid);

    //添加留言
    int insertGuestBook(Guestbook guestbook);

    //根据id删除留言信息
    int deleteGuestbookById(String id);

    //根据id改变留言状态的值
    int updateStatus(Guestbook guestbook);



}
