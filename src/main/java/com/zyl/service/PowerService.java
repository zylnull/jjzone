package com.zyl.service;

import com.zyl.entity.Power;
import com.zyl.mapper.PowerMapper;
import com.zyl.security.JwtGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PowerService {

    @Autowired
    private PowerMapper powerMapper;

    public List<JwtGrantedAuthority> findPowers(String username){
        List<Power> powers = powerMapper.selectPowersByUsername(username);
        //包装成Security所需要的类型
        List<JwtGrantedAuthority> authorityList = powers.stream().map(Power::getPpath).collect(Collectors.toList())
                .stream().map(JwtGrantedAuthority::new).collect(Collectors.toList());
        return authorityList;
    }
}
