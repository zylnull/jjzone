package com.zyl.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Date;

//jwt工具类
public class JwtUtil {
    //密钥
    private final static String SECRET = "ZYLINWUHAN";
    // 过期时间(45分钟)
    private final static Duration EXPIRATION = Duration.ofMinutes(45);

    //构建字符串(根据用户名构建字符串)
    public static String generate(String username){
        Date expDate =new Date(System.currentTimeMillis() + EXPIRATION.toMillis());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    //解析JWT字符串
    // Claims: 荷载部分，其中包括Subject  expTime  issued
    public static Claims parse(String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }
        Claims claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (JwtException exception){
            exception.printStackTrace();
        }
        return claims;
    }
}
