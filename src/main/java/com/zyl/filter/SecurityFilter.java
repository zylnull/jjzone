package com.zyl.filter;

import com.zyl.expection.JwtParseErrorException;
import com.zyl.expection.TokenIsNoneException;
import com.zyl.jwt.JwtUtil;
import com.zyl.security.JwtGrantedAuthority;
import com.zyl.service.PowerService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

//jwt过滤器
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private PowerService powerService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws ServletException, IOException {
        if (req.getRequestURI().endsWith("login") || req.getRequestURI().endsWith("register") || req.getRequestURI().endsWith("test")){//如果获取请求的后缀uri为login req则放行
            filterChain.doFilter(req,resp);
            return;
        }
        if (req.getMethod().equalsIgnoreCase("OPTIONS")){//如果是OPTIONS请求则放行
            filterChain.doFilter(req,resp);
            return;
        }
        //从请求头获取token
        String token = req.getHeader("Authorization");//Authorization可以自定义
        if (StringUtils.isEmpty(token)){
            throw new TokenIsNoneException("token为空");
        }
        //解析token
        Claims claims = JwtUtil.parse(token);
        if (claims==null){
            throw new JwtParseErrorException("token解析异常，jwt解析失败！");
        }
        //权限集合
        List<JwtGrantedAuthority> authorities = powerService.findPowers(claims.getSubject());
        //存入安全上下文
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        claims.getSubject(),
                        null,
                        authorities
                )
        );
        filterChain.doFilter(req,resp);

    }
}
