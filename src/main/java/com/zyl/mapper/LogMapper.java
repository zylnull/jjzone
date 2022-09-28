package com.zyl.mapper;

import com.zyl.entity.Log;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface LogMapper {

    List<Log> selectAllLog();

    int insertSelective(Log systemLog);
}
