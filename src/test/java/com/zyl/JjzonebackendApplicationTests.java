package com.zyl;

import com.zyl.entity.Admin;
import com.zyl.mapper.AdminMapper;
import com.zyl.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class JjzonebackendApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testMapper(){
//  $2a$10$yXmViDkaO5IRhmDHIsIQ/ep5naQTpA3O1y1yz98lEldpkv/Ipfnh2
        System.out.println(passwordEncoder.encode("12345678"));
        System.out.println(passwordEncoder.matches("123","$2a$10$8EzcllDhGQjN3fVEM5dSYuDkHLTZsVzeiWs4FiR/0X/hlnJtEWEJq"));
    }

    @Test
    public void  test(){
        System.out.println((1.1  + 1) + "now!");
        System.out.println();
        int a = 1;
        String b = "ylc!";
        System.out.println(a + a  + b);
        System.out.println(a + (a  + b));
       String c =  a + a + b;
        System.out.println((a + a)  + b);


    }

}
