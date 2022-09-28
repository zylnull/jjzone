package com.zyl;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
@MapperScan("com.zyl.mapper")
public class JjzonebackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JjzonebackendApplication.class, args);
//        log.warn("项目启动成功！请访问 http://localhost:8080");
    }

}
