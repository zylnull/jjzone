package com.zyl.entity;

import lombok.Data;

@Data
public class Role {

  private long rid;
  private String rname;

  public Role() {
  }

  public Role(long rid, String rname) {
    this.rid = rid;
    this.rname = rname;
  }
}
