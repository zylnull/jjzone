package com.zyl.entity;

import lombok.Data;

@Data
public class User {

  private Integer uid;
  private String username;
  private String password;
  private String nickname;
  private String sex;
  private String birthday;
  private String email;
  private String address;
  private String avater;
  private String phone;
  private String createTime;
  private Integer rid;

  public User() {
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

}
