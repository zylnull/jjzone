package com.zyl.expection;

import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class MyException implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
        //全局异常处理
        Map<String,Object> map=new HashMap<>();
        String message;
        //错误码默认403 无权限登录
        int code =403;
        if (e instanceof UsernameNotFoundException){
            //用户名不存在，返回前端 403 用户名不存在
            message="用户名不存在";
        }else if (e instanceof BadCredentialsException){
            //密码错误 返回前端403 密码错误
            message="密码错误";
        }else {
            //其余情况，返回系统出现异常  错误码标记为500
            code=500;
            message="系统出现异常";
        }
        map.put("code",code);
        map.put("message",message);
        //map返回给前端 设置输出字符集和方式
        resp.setContentType("application/json;charset=utf8");
        //输出流写出
        PrintWriter writer = resp.getWriter();
        writer.print(JSON.toJSONString(map));
        //关闭流
        writer.close();

    }
}
