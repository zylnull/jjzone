package com.zyl.mapper;

import com.zyl.entity.Notice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NoticeMapper {
    //查询所有公告
    List<Notice> selectAllNotice();

    //添加公告
    int insertNotice(Notice notice);

    //根据id删除公告！
    int deleteById(String id);

    //修改公告
    int updateNotice(Notice notice);
}
