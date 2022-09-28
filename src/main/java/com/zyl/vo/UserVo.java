package com.zyl.vo;

import lombok.Data;

@Data
public class UserVo {
    private Integer uid;
    private String username;
    private String token;
    private Integer rid;

    public UserVo() {
    }

    public UserVo(String username, String token, Integer rid) {
        this.username = username;
        this.token = token;
        this.rid = rid;
    }

    public UserVo(Integer uid, String username, String token, Integer rid) {
        this.uid = uid;
        this.username = username;
        this.token = token;
        this.rid = rid;
    }
}
