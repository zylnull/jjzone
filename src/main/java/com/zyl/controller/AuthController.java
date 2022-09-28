package com.zyl.controller;

import com.zyl.http.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/authentication")
    public R authentication(){
        return R.ok(200,"验证通过",111);
    }

}
