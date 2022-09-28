package com.zyl.mapper;

import com.zyl.entity.Power;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PowerMapper {

    List<Power> selectPowersByUsername(String username);

    List<Power> selectPowersByAdminName(String username);
}
