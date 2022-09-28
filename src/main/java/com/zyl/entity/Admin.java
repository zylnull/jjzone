package com.zyl.entity;

import lombok.Data;

@Data
public class Admin {

  private Integer aid;
  private String username;
  private String password;
  private Integer rid;

  public Admin() {
  }

  public Admin(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Admin(Integer aid, String username, String password, Integer rid) {
    this.aid = aid;
    this.username = username;
    this.password = password;
    this.rid = rid;
  }
}
